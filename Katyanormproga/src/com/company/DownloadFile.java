package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class DownloadFile {
    public static void main(String[] args) throws IOException {

        URL fetchWebsite = new URL("https://drive.google.com/drive/folders/16CdayCLTxEs9Xy41tnQb9lPE6okEDWG1?usp=sharing");
        ReadableByteChannel readableByteChannel = Channels.newChannel(fetchWebsite.openStream());

        try (FileOutputStream fos = new FileOutputStream("Main.jar")) {
            fos.write(22);
//            fos.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        }


    }
}