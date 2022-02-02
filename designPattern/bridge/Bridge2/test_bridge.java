interface FileUploader {
    Object upload(String path);
    boolean check(Object object);
}

class FileUploaderImpl implements FileUploader {
    private FileUploadExcutor excutor = null;
    public FileUploaderImpl(FileUploadExcutor excutor) {
        this.excutor = excutor;
    }

    public Object upload(String path) {
        return excutor.uploadFile(path);
    }
    @Override
    public boolean check(Object object) {
        return excutor.checkFile(object);
    }
}

interface FileUploadExcutor {
    Object uploadFile(String path);
    boolean checkFile(Object object);
}

class LinuxFileUpLoadExcutor implements FileUploadExcutor {
    @Override
    public Object uploadFile(String path) {
        return null;
    }
    @Override
    public boolean checkFile(Object object) {
        return false;
    }
}
class WindowsFileUpLoadExcutor implements FileUploadExcutor {
    @Override
    public Object uploadFile(String path) {
        return null;
    }
    @Override
    public boolean checkFile(Object object) {
        return false;
    }
}

class demo {
    public static void main(String[] args) {
      new FileUploaderImpl(new LinuxFileUpLoadExcutor()).check(null);
    }
}


