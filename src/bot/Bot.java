/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;


public class Bot extends javax.swing.JFrame {
    final String PRP = "I you he she it we they what who";  //personal pronouns
    final String POS = "mine yours his hers ours theirs";  //possessive pronouns
    List<String> places = Arrays.asList("s", "s", "a");
    
    /**
     * Creates new form NewJFrame
     */
    public Bot() {
        initComponents();
        sendMessage("Bot: Hello! Before you start asking me questions, give me some information about your world.\n\n");
        sendMessage("Bot: coz idk shit about things.\n\n");
    }
    
    void sendMessage(String message){
        this.jTextArea1.append(message);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    String userMessage = "";
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        userMessage = jTextField1.getText();
        jTextField1.setText("");
        jTextArea1.setText(jTextArea1.getText() + "You: " + userMessage + "\n\n");
        understand(userMessage);
    }//GEN-LAST:event_jButton1ActionPerformed
    private void understand(String userMessage){
        
        // label incoming message as Question OR information and process accordingly
        if(userMessage.charAt(userMessage.length()-1) == '?'){
            answer(userMessage);
        }
        else {
            learn(userMessage);
        }
        
    }
    void answer(String userMessage){
        search(removeStopwords(userMessage));

    }
    int search(ArrayList<String> userMessage){
        ArrayList<String> fileContent = new ArrayList<>();
        try{
            Scanner read = new Scanner (new FileInputStream("/Users/Sina/NetBeansProjects/Bot/src/bot/data.txt"));
            while(read.hasNextLine()){
                String line = read.nextLine();
                fileContent.add(line);
            }
            read.close();
        }
        catch (FileNotFoundException e){
            System.out.println("fuck");
        }
        for(int i = 0; i < fileContent.size(); i++){
            System.out.println(fileContent.get(i));
        }
        return 0;
    }
    void learn(String userMessage){
        store(removeStopwords(userMessage), userMessage);
        this.jTextArea1.setText(this.jTextArea1.getText() + "Bot: " + "Cool! I'll remember that." + "\n\n");
    }
    
    int messageID = 0;
    
    
    void store(ArrayList<String> keywords, String userMessage){
        
        this.messageID += 1;
        try{
            PrintWriter write = new PrintWriter(new FileOutputStream("/Users/Sina/NetBeansProjects/Bot/src/bot/data.txt", true));
            write.print(this.messageID + ": ");
            for(int i = 0; i < keywords.size(); i++){
                if(i != keywords.size() -1 ){
                    write.print(keywords.get(i) + ",");
                }
                else
                    write.print(keywords.get(i) + "\t | \t" + userMessage + "\n");
            }
            write.close();
        }
        catch(IOException e){
            System.out.println("gg");
        }
    }
    ArrayList<String> removeStopwords(String sentence){
        String[] stopwords = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours", "yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its", "itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this", "that", "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having", "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while", "of", "at", "by", "for", "with", "about", "against", "between", "into", "through", "during", "before", "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under", "again", "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both", "each", "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same", "so", "than", "too", "very", "s", "t", "can", "will", "just", "don", "should", "now"};
        ArrayList<String> targets = new ArrayList<>();
        for (int i = 0; i < sentence.split(" ").length; i++){
            targets.add(sentence.split(" ")[i]);
        }
        for (int i = 0; i < targets.size(); i++){
            for (int j = 0; j < stopwords.length; j++){
                if(targets.get(i).equalsIgnoreCase(stopwords[j])){
                    targets.remove(i);
                }
            }
        }
        return targets;
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Bot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bot().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
