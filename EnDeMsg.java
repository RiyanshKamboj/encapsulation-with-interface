package Messsage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import java.awt.Font;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EnDeMsg extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	static String textdata_1;
	static String textdata_2;
	
	
	/**
	 * Encryption and Decryption Working
	 * 
	 */
	
	
	private static SecretKeySpec secretKey;
	private static byte[] key;
	private static String encryptedString;  
	private static String decryptedString; 

	public static void setKey(String myKey) 
	{
		System.out.println("inside setKey");
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); 
			secretKey = new SecretKeySpec(key, "AES");
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("inside setKey");
	}

	public static String encrypt(String strToEncrypt, String secret) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException 
	{
		System.out.println("inside ennnn");
		//try 
		//{
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
//		} 
//		catch (Exception e) 
//		{
//			System.out.println("Error while encrypting: " + e.toString());
//		}
	 
	}

	public static String decrypt(String strToDecrypt, String secret) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
	{
		//try 
		//{
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
//		} 
//		catch (Exception e) 
//		{
//			System.out.println("Error while decrypting: " + e.toString());
//		}
//		return null;
	}

	
	
	/**
	 * Launch the application.
	 * 
	 * 
	 */
	final String SECRET_KEY = "ssshhhhhhhhhhh!!!!";
	public static void main(String[] args) {
	
//		System.out.println("enter the message to encrypt and decrypt");
		//String str=sc.nextLine();
//		
//		String originalString = textdata_1;
//		String encryptedString = ENCRP.encrypt(originalString, secretKey) ;
//		String decryptedString = ENCRP.decrypt(encryptedString, secretKey) ;
//		
//		System.out.println(originalString);
//		System.out.println(encryptedString);
//		System.out.println(decryptedString);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnDeMsg frame = new EnDeMsg();
					frame.setVisible(true);
					
				//	Scanner sc= new Scanner(System.in);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EnDeMsg() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(12, 63, 258, 83);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(321, 63, 258, 83);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnEncryptMessage = new JButton("Encrypt Message");
		btnEncryptMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 textdata_1 =textField.getText();
					String originalString = textdata_1;
					try {
						encryptedString = EnDeMsg .encrypt(originalString, SECRET_KEY) ;
					} catch (InvalidKeyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchPaddingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalBlockSizeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BadPaddingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    //decryptedString = EnDeMsg .decrypt(encryptedString, secretKey) ;
					
					System.out.println(originalString);
					System.out.println(encryptedString);
//					System.out.println(decryptedString);
//				 System.out.println(encryptedString);
				 textField_2.setText(encryptedString);
				 
			}
		});
		btnEncryptMessage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnEncryptMessage.setBounds(97, 159, 173, 25);
		contentPane.add(btnEncryptMessage);
		
		JButton btnDecrypMessage = new JButton("Decrypt Message");
		btnDecrypMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encryptedString=textField_1.getText();
				
				 try {
					decryptedString = EnDeMsg .decrypt(encryptedString, SECRET_KEY) ;
				} catch (InvalidKeyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalBlockSizeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					} catch (BadPaddingException e1) {
		
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				 System.out.println(decryptedString);
				 textField_3.setText(decryptedString);
				
			}
		});
		btnDecrypMessage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDecrypMessage.setBounds(406, 159, 173, 25);
		contentPane.add(btnDecrypMessage);
		
		JLabel lblEncryptedMessage = new JLabel("Encrypted Message");
		lblEncryptedMessage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEncryptedMessage.setBounds(12, 240, 188, 25);
		contentPane.add(lblEncryptedMessage);
		
		JLabel lblDecryptedMessage = new JLabel("Decrypted Message");
		lblDecryptedMessage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDecryptedMessage.setBounds(321, 243, 188, 19);
		contentPane.add(lblDecryptedMessage);
		
		textField_2 = new JTextField();
		textField_2.setBounds(12, 275, 258, 83);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(321, 275, 258, 83);
		contentPane.add(textField_3);
	}
}
