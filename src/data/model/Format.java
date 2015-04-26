package data.model;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class Format {

	private String idFormat;
	private String version;
	private String nameFormat;
	private String extension;
	private String aprobado;
	private String actived;
	private String processId;
	private String userLastModification;

	public String getIdFormat() {
		return idFormat;
	}

	public void setIdFormat(String idFormat) {
		this.idFormat = idFormat;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getNameFormat() {
		return nameFormat;
	}

	public void setNameFormat(String nameFormat) {
		this.nameFormat = nameFormat;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getAprobado() {
		return aprobado;
	}

	public void setAprobado(String aprobado) {
		this.aprobado = aprobado;
	}

	public String getActived() {
		return actived;
	}

	public void setActived(String actived) {
		this.actived = actived;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public void moveFormat(String from, String to) throws IOException {
		Path FROM = Paths.get(from);
		Path TO = Paths.get(to);
		Files.move(FROM, TO, StandardCopyOption.REPLACE_EXISTING);
	}
	public void deleteFile(String path) throws IOException {
		Path file = Paths.get(path);
		Files.delete(file);
	}

	public String getUserLastModification() {
		return userLastModification;
	}

	public void setUserLastModification(String userLastModification) {
		this.userLastModification = userLastModification;
	}
	

}
