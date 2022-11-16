package model.common;

import java.io.File;
import java.io.Serializable;

public class FileInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    //duong dan toi mai server
    private String destinationDirectory;
    
    //dương dan tren may hien tai
    private String sourceDirectory;
    private String filename;
    private long fileSize;
    private int piecesOfFile;
    private int lastByteLength;
    private byte[] dataBytes;
    
    

    public FileInfo(String sourceDirectory) {
		this.sourceDirectory = sourceDirectory;
		
		File sourceFile = new File(sourceDirectory);
		byte[] fileBytes = new byte[(int) sourceFile.length()];
		this.setFilename(sourceFile.getName());
        this.setDataBytes(fileBytes);
	}

	public String getDestinationDirectory() {
        return destinationDirectory;
    }

    public void setDestinationDirectory(String destinationDirectory) {
        this.destinationDirectory = destinationDirectory;
    }

    public String getSourceDirectory() {
        return sourceDirectory;
    }

    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getDataBytes() {
        return dataBytes;
    }

    public void setDataBytes(byte[] dataBytes) {
        this.dataBytes = dataBytes;
    }

}
