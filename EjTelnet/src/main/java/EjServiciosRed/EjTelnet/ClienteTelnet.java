package EjServiciosRed.EjTelnet;


import java.io.InputStream;
import java.io.PrintStream;
import org.apache.commons.net.telnet.TelnetClient;

public class ClienteTelnet {
private TelnetClient telnet = new TelnetClient();
private InputStream in;
private PrintStream out;
private String prompt = "$";

public ClienteTelnet(String server, String user, String password) {
try {
    // Connect to the specified server
    telnet.connect(server, 2323);

    // Get input and output stream references
    in = telnet.getInputStream();
    out = new PrintStream(telnet.getOutputStream());

    // Log the user on
    readUntil("login: ");
    write(user);
    readUntil("Password: ");
    write(password);

    // Advance to a prompt
     readUntil(prompt+ "");
   
} catch (Exception e) {
    e.printStackTrace();
}
}

public void su(String password) {
try {
    write("su");
    readUntil("Password: ");
    write(password);
    prompt = "#";
    readUntil(prompt + "");
} catch (Exception e) {
    e.printStackTrace();
}
}

public String readUntil(String pattern) {
try {
    char lastChar = pattern.charAt(pattern.length() - 1);
    StringBuffer sb = new StringBuffer();
    boolean found = false;
    char ch = (char) in.read();
    while (true) {
        System.out.print(ch);
        sb.append(ch);
        if (ch == lastChar) {
            if (sb.toString().endsWith(pattern)) {
                return sb.toString();
            }
        }
        ch = (char) in.read();
    }
    } catch (Exception e) {
    e.printStackTrace();
}
return null;
}

public void write(String value) {
try {
    out.println(value);
    out.flush();
    System.out.println(value);
} catch (Exception e) {
    e.printStackTrace();
}
}
public String sendCommand(String command) {
try {
    write(command);
    return readUntil(prompt + "");
} catch (Exception e) {
    e.printStackTrace();
}
return null;
}

public void disconnect() {
try {
    telnet.disconnect();
} catch (Exception e) {
    e.printStackTrace();
}
}

public static void main(String[] args) {
try {
	System.out.println("Connection...");
    ClienteTelnet telnet = new ClienteTelnet(
            "127.0.0.1", "ahmed", "ahmed");
    System.out.println("Got Connection...");
    telnet.sendCommand("pwd ");
    System.out.println("run command pwd");
    telnet.sendCommand("ps -ef ");
    System.out.println("run command ps -ef");
    telnet.sendCommand("ls -la ");
    System.out.println("run command ls");
    telnet.disconnect();
    System.out.println("DONE"); 
} catch (Exception e) {
    e.printStackTrace();
}
}
}