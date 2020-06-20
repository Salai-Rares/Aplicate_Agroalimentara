package AbstractClasses;

import Models.Produs;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class SavePhotos  {
    public static String SavePhotos(Object a,String nume_director){
        FileChooser fc=new FileChooser();
        String path = new String();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG files","*.jpg"),
                new FileChooser.ExtensionFilter("PNG files","*.png"));

        File selectedFile=fc.showOpenDialog(null);
        try{
            Image img=new Image(selectedFile.toURI().toURL().toString());


            if(selectedFile != null) {

                MakeaDir.makeaDir(nume_director);
                String name = selectedFile.getName();
                String extension = name.substring(1 + name.lastIndexOf(".")).toLowerCase();
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(img, null);
                if(a instanceof Produs) {
                    ((Produs) a).setPath("file:Photos/" + name );
                path="file:Photos/"+name;
                }
                ImageIO.write(bufferedImage, extension, new File(nume_director+"//"+name/*+"."+extension*/));

            }
       }catch (IOException e){
            e.printStackTrace();
        }
        return path;
    }
}
