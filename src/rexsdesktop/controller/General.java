/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.security.Key;
import rexsdesktop.model.Db;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import rexsdesktop.CurrentUser;
import rexsdesktop.controller.Scalr.*;
import rexsdesktop.model.ENV;

/**
 * Clase que contiene Métodos generales del sistema.
 *
 * @author Eduardo
 * @version 1.2
 */
public class General {

    /**
     * Método para generar un respaldo de los datos.
     *
     * @param directoryPath direccion del archivo
     * @param backup vector donde generar el Backup
     * @return retorna un valor booleano.
     */
    public static boolean generarBackup(String directoryPath, String[][] backup) {
        boolean r = false;
        try {
            List<String> files = new ArrayList<String>();
            for (int i = 0; i < backup.length; i++) {
                System.out.println(backup.length);
                Db db = new Db();
                ResultSet rs = db.sqlToCSV(backup[i][0]);
                if (rs != null) {
                    String filePath = directoryPath + "\\" + backup[i][1] + ".rexs";
                    System.out.println(filePath);
                    files.add(filePath);
                    FileWriter fw = new FileWriter(filePath);
                    int cols = rs.getMetaData().getColumnCount();
                    while (rs.next()) {

                        for (int j = 1; j <= cols; j++) {
                            fw.append(rs.getString(j));
                            if (j < cols) {
                                fw.append(',');
                            }
                        }
                        fw.append('\n');
                    }
                    fw.flush();
                    fw.close();

                    File f = new File(filePath);
                    encryption(Cipher.ENCRYPT_MODE, ENV.ENCRYPTION_KEY, f);
                }
            }
            if (!files.isEmpty()) {
                zipFiles(files, directoryPath);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);;
        }
        return r;
    }

    public static void zipFiles(List<String> srcFiles, String directoryPath) throws FileNotFoundException, IOException {
        //List<String> srcFiles = Arrays.asList("test1.txt", "test2.txt");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy-HHmmss");
        LocalDateTime now = LocalDateTime.now();
        String d = dtf.format(now);
        FileOutputStream fos = new FileOutputStream(directoryPath + "\\" + "backupRexs" + d + ".zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (String srcFile : srcFiles) {
            File fileToZip = new File(srcFile);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
            fileToZip.delete();
        }
        zipOut.close();
        fos.close();
    }

    /**
     * Método utilizado para encriptar
     *
     * @param cipherMode
     * @param key
     * @param inputFile
     */
    public static void encryption(int cipherMode, String key, File inputFile) {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            if (cipherMode == Cipher.ENCRYPT_MODE) {
                FileOutputStream outputStream = new FileOutputStream(inputFile);
                outputStream.write(outputBytes);

                inputStream.close();
                outputStream.close();
            }
            //System.out.println(new String(outputBytes));

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static String[][] merge(String[][]... arrays) {
        int finalLength = 0;
        for (String[][] array : arrays) {
            if (array != null) {
                finalLength += array.length;
            }
        }

        String[][] dest = null;
        int destPos = 0;

        for (String[][] array : arrays) {
            if (dest == null) {
                if (array != null) {
                    dest = Arrays.copyOf(array, finalLength);
                    destPos = array.length;
                }

            } else {
                if (array != null) {
                    System.arraycopy(array, 0, dest, destPos, array.length);
                    destPos += array.length;
                }
            }
        }
        return dest;
    }

    public static BufferedImage resizeAndCropIMG(BufferedImage img) throws IOException {
        BufferedImage small;
        BufferedImage cropImage;
        int h = img.getHeight();
        int w = img.getWidth();
        if (h > w || h == w) {
            small = Scalr.resize(img,
                    Method.AUTOMATIC,
                    Mode.FIT_TO_WIDTH,
                    200, 200,
                    Scalr.OP_ANTIALIAS);
        } else {
            small = Scalr.resize(img,
                    Method.AUTOMATIC,
                    Mode.FIT_TO_HEIGHT,
                    200, 200,
                    Scalr.OP_ANTIALIAS);
        }
        cropImage = Scalr.crop(small, (small.getWidth() - 200) / 2, (small.getHeight() - 200) / 2, 200, 200);
        small.flush();
        img.flush();
        //File outputfile = new File("C:\\Users\\Eduardo\\Documents\\prueba2.jpg");
        //System.out.println(ImageIO.write(cropImage, "jpg", outputfile));
        return cropImage;
    }

    public static BufferedImage resizeSquare(BufferedImage img, int size) throws IOException {
        BufferedImage imgR = Scalr.resize(img,
                Method.AUTOMATIC,
                Mode.AUTOMATIC,
                size, size,
                Scalr.OP_ANTIALIAS);
        img.flush();
        return imgR;
    }

    public static String getMapwizeAPIKey() {
        Db db = new Db();
        return db.getMapwizeAPIKey();
    }

    public static String getMapwizeVenueID() {
        Db db = new Db();
        return db.getMapwizeVenueID();
    }

    public static boolean setMapwizeAPIKey(String value) {
        Db db = new Db();
        return db.setMapwizeAPIKey(value);
    }

    public static boolean setMapwizeVenueID(String value) {
        Db db = new Db();
        return db.setMapwizeVenueID(value);
    }

    public static String getEdicion() {
        Db db = new Db();
        try {
            String fecha = db.getEdicion();
            CurrentUser.edicionExpotecnica = fecha;
            return fecha;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Vacio";
    }

    public static boolean setEdicion(String edicion) {
        Db db = new Db();
        try {
            if (db.setEdicion(edicion)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
        return false;
    }

    public static void agregarBitacora(String accion, int idUsuario) {
        Db db = new Db();
        int idAccion = db.getIdAccionBitacora(accion);
        if (idAccion != -1) {
            db.agregarBitacora(idAccion, idUsuario);
        }
    }

    public static BufferedImage resizeAndCropIMG2(BufferedImage img, int width, int height) throws IOException {
        BufferedImage small;
        BufferedImage cropImage;
        int h = img.getHeight();
        int w = img.getWidth();
        if (h > w || h == w) {
            small = Scalr.resize(img,
                    Method.AUTOMATIC,
                    Mode.FIT_TO_WIDTH,
                    width, height,
                    Scalr.OP_ANTIALIAS);
        } else {
            small = Scalr.resize(img,
                    Method.AUTOMATIC,
                    Mode.FIT_TO_HEIGHT,
                    width, height,
                    Scalr.OP_ANTIALIAS);
        }
        cropImage = Scalr.crop(small, (small.getWidth() - width) / 2, (small.getHeight() - height) / 2, width, height);
        small.flush();
        img.flush();
        //File outputfile = new File("C:\\Users\\Eduardo\\Documents\\prueba2.jpg");
        //System.out.println(ImageIO.write(cropImage, "jpg", outputfile));
        return cropImage;
    }

    public static BufferedImage resizeSquare2(BufferedImage img, int size) throws IOException {
        BufferedImage imgR = Scalr.resize(img,
                Method.AUTOMATIC,
                Mode.AUTOMATIC,
                size, size,
                Scalr.OP_ANTIALIAS);
        img.flush();
        return imgR;
    }

    public static int usuariosNuevosHoy() {
        return new Db().usuariosNuevosHoy();
    }

    public static int votosNuevosHoy() {
        return new Db().votosNuevosHoy();
    }

    public static double porcentajeUsuariosNuevosDia() {
        Db db = new Db();
        boolean aumento = true;
        double r = 0;
        int usuariosHoy = db.usuariosNuevosHoy();
        int usuariosAyer = db.usuariosNuevosAyer();
        if (usuariosHoy == usuariosAyer) {
            r = 0;
        } else if (usuariosHoy > usuariosAyer) {
            double incremento = (double) usuariosHoy - (double) usuariosAyer;
            if (usuariosAyer != 0) {
                r = incremento / usuariosAyer;
            } else {
                r = incremento;
            }
        } else if (usuariosHoy < usuariosAyer) {
            aumento = false;
            double disminucion = (double) usuariosAyer - (double) usuariosHoy;
            r = disminucion / usuariosAyer;
        }
        if (aumento) {
            return r * 100;
        } else {
            return r * (-100);
        }
    }

    public static double porcentajeVotosNuevosDia() {
        Db db = new Db();
        boolean aumento = true;
        double r = 0;
        int votosHoy = db.votosNuevosHoy();
        int votosAyer = db.votosNuevosAyer();
        if (votosHoy == votosAyer) {
            r = 0;
        } else if (votosHoy > votosAyer) {
            double incremento = (double) votosHoy - (double) votosAyer;
            if (votosAyer != 0) {
                r = incremento / votosAyer;
            } else {
                r = incremento;
            }
        } else if (votosHoy < votosAyer) {
            aumento = false;
            double disminucion = (double) votosAyer - (double) votosHoy;
            r = disminucion / votosAyer;
        }
        if (aumento) {
            return r * 100;
        } else {
            return r * (-100);
        }
    }

    public static CategoryDataset createDatasetChartInicioSesion() {

        DefaultCategoryDataset result = new DefaultCategoryDataset();
        Db db = new Db();
        final String series1 = "Hoy";
        final String series2 = "Ayer";
        for (int i = -7; i <= 0; i++) {
            int count = db.countIniciosSesion(i, 0);
            String hora = db.horaInicioSesion(i);
            result.addValue(count, series1, hora);
            int count2 = db.countIniciosSesion(i, -1);
            result.addValue(count2, series2, hora);
            System.out.println(count);
            System.out.println(hora);
        }
        return result;
    }

    public static int countUsuarios() {
        return new Db().countUsuarios();
    }
    
    public static int countVotos() {
        return new Db().countVotos();
    }
    
    public static int countProyectos() {
        return new Db().countProyectos();
    }
    
    public static int countUbicacion() {
        return new Db().countUbicacion();
    }
}
