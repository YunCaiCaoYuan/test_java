import java.io.*;
import java.util.Base64;
import java.util.zip.*;

interface DataLoader {
    String read();
    void write(String data);
}

class BaseFileDataLoader implements DataLoader {
    private String filePath;
    public BaseFileDataLoader(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public String read() {
        char[] buffer = null;
        File file = new File(filePath);
        try (FileReader reader = new FileReader(file)) {
            buffer = new char[(int) file.length()];
            reader.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(buffer);
    }
    @Override
    public void write(String data) {
        File file = new File(filePath);
        try (OutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DataLoaderDecorator implements DataLoader {
    private DataLoader wrapper;
    public DataLoaderDecorator(DataLoader wrapper) {
        this.wrapper = wrapper;
    }
    @Override
    public String read() {
        return wrapper.read();
    }
    @Override
    public void write(String data) {
        wrapper.write(data);
    }

}
class EncryptionDataDecorator extends DataLoaderDecorator{
    public EncryptionDataDecorator(DataLoader wrapper) {
        super(wrapper);
    }
    @Override
    public String read() {
        return decode(super.read());
    }
    @Override
    public void write(String data) {
        super.write(encode(data));
    }
    private String encode(String data) {
        byte[] result = data.getBytes();
        for (int i = 0; i < result.length; i++) {
            result[i] += (byte) 1;
        }
        return Base64.getEncoder().encodeToString(result);
    }
    private String decode(String data) {
        byte[] result = Base64.getDecoder().decode(data);
        for (int i = 0; i < result.length; i++) {
            result[i] -= (byte) 1;
        }
        return new String(result);
    }
}
class CompressionDataDecorator extends DataLoaderDecorator{
    public CompressionDataDecorator(DataLoader wrapper) {
        super(wrapper);
    }
    @Override
    public String read() {
        return decompress(super.read());
    }
    @Override
    public void write(String data) {
        super.write(compress(data));
    }
    private String compress(String stringData) {
        byte[] data = stringData.getBytes();
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
            DeflaterOutputStream dos = new DeflaterOutputStream(bout, new Deflater());
            dos.write(data);
            dos.close();
            bout.close();
            return Base64.getEncoder().encodeToString(bout.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private String decompress(String stringData) {
        byte[] data = Base64.getDecoder().decode(stringData);
        try {
            InputStream in = new ByteArrayInputStream(data);
            InflaterInputStream iin = new InflaterInputStream(in);
            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
            int b;
            while ((b = iin.read()) != -1) {
                bout.write(b);
            }
            in.close();
            iin.close();
            bout.close();
            return new String(bout.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class demo {
    public static void main(String[] args) {
        String testinfo = "Name, testinfo\nMia, 10000\nMax, 9100";
        DataLoaderDecorator encoded = new CompressionDataDecorator(
                new EncryptionDataDecorator(
                        new BaseFileDataLoader("demo.txt")));
        encoded.write(testinfo);
        DataLoader plain = new BaseFileDataLoader("demo.txt");
        System.out.println("- 输入 ----------------");
        System.out.println(testinfo);
        System.out.println("- 加密+压缩 写入文件--------------");
        System.out.println(plain.read());
        System.out.println("- 解密+解压 --------------");
        System.out.println(encoded.read());
    }
}