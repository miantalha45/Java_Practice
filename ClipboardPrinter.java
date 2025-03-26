import java.awt.*;
import java.awt.datatransfer.*;

public class ClipboardPrinter {
    public static void main(String[] args) {
        try {
        
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        
            Transferable transferable = clipboard.getContents(null);
            
            
            if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                
                String clipboardText = (String) transferable.getTransferData(DataFlavor.stringFlavor);
            
                System.out.println("Clipboard content: " + clipboardText);
            } else {
                System.out.println("No text found on the clipboard.");
            }
        } catch (Exception e) {
            System.out.println("Error accessing clipboard: " + e.getMessage());
        }
    }
}