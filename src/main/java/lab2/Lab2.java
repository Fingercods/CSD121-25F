// packages used:
// javax.imageio is for reading and writing images
// javax.swing is for user interface stuff like windows, buttons, labels
// java.awt is for graphics things like color, image, layouts
// java.io is for input and output streams
// java.net.http is for making web requests and getting responses

import javax.imageio.ImageIO;   // class in javax.imageio to read and write images
import javax.swing.*;           // package with JFrame, JLabel and more
import java.awt.*;              // package with colour, image, border
import java.io.IOException;     // for handling input and output errors
import java.io.InputStream;     // stream of bytes, like when an image comes from web
import java.net.URI;            // class for making a web link
import java.net.http.HttpClient;  // class, can be to send web requests
import java.net.http.HttpRequest; // makes request object
import java.net.http.HttpResponse; // holds the answer from web server

void main() {
    try {

        var avatarStream = getRandomAvatarStream(); // avatarStream type is reference

        showAvatar(avatarStream); //shows the image
    } catch (IOException | InterruptedException e) {

        e.printStackTrace();// it is an instance method, it prints the error
    }
}

InputStream getRandomAvatarStream() throws IOException, InterruptedException {

    String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
    // styles is an array which is a String object used as a reference
    //

    var style = styles[(int)(Math.random() * styles.length)]; // Math.random is a class method, returns double between 0 and 1. Style is string of reference type & styles.length is  instance variable

    var seed = (int)(Math.random() * 10000); // multiplies by 1000

    var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed)); //.formatted is an instance method as it replaces %variable with values
    // URI.create is a class method which is making uri

    var request = HttpRequest.newBuilder(uri).build();//HttpRequest.newBuilder is a class method and build is an instance method, makes HttpRequest

    try (var client = HttpClient.newHttpClient()) {
        // HttpClient.newHttpClient is a class method and  makes HttpClient object
        var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream()); // client.send is an instance method which sends request and retrieves response . HttpResponse.BodyHandlers.ofInputStream is a class method, tells to get data as InputStream
        return response.body(); // response.body is an instance method , it returns inputStream back
    }
}

void showAvatar(InputStream imageStream) {  // imageStream is reference type
    JFrame frame = new JFrame("PNG Viewer"); // new JFrame makes a new page .
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //frame.setDefaultCloseOperation is an instance method .JFrame.EXIT_ON_CLOSE is a class variable (constant)

    frame.setResizable(false); // instance method
    frame.setSize(200, 200);   // instance method
    frame.getContentPane().setBackground(Color.BLACK); // frame.getContentPane is an instance method which returns container .  setBackground is an instance method, it sets background color// Color.BLACK is a constant class variable

    try {
        Image image = ImageIO.read(imageStream);// ImageIO.read is a class method, reads InputStream and returns Image (type of reference)

        JLabel imageLabel = new JLabel(new ImageIcon(image));// new ImageIcon(image) is a constructor and created an icon .new JLabel is also a constructor and makes a label
        frame.add(imageLabel, BorderLayout.CENTER);// frame.add is an instance method BorderLayout.CENTER is a class variable and is constant.
    } catch (IOException e) {
        e.printStackTrace(); // instance method
    }

    frame.setVisible(true); // instance method and if true then shows
}
