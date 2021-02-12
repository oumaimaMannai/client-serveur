import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
/*
 * www.codeurjava.com
 */ 
public class Client2 {
	public static void main(String[] args) throws ClassNotFoundException {
		final Socket clientSocket;
		final ObjectInputStream in;
		final ObjectOutputStream out;
		final Scanner sc = new Scanner(System.in);//pour lire � partir du clavier 
		
		int n;
		int[][] A;
		int[][] B;
		int[][] C;		
		
		System.out.println(" saisie n = ");
		n = sc.nextInt();
		
		while ( n <= 0 ) {
			System.out.println(" saisie n = ");
			n = sc.nextInt();			
		}
		
		A = new int[n][n];
		B = new int[n][n];
		
		
		System.out.println("remplir la matrice A :");
		for (int i = 0 ;i<n; i++) {
			System.out.println("ligne : "+i);
			for (int j=0; j<n ; j++) {				
				System.out.println("A["+i+"]["+j+"] = ");
				A[i][j] = sc.nextInt();
			}
		}
		
		System.out.println("remplir la matrice B :");
		for (int i = 0 ;i<n; i++) {
			System.out.println("ligne : "+i);
			for (int j=0; j<n ; j++) {				
				System.out.println("B["+i+"]["+j+"] = ");
				B[i][j] = sc.nextInt();
			}
		}
		
		
		try {
			/*
			* les informations du serveur ( port et adresse IP ou nom d'hote
			* 127.0.0.1 est l'adresse local de la machine
			*/
			clientSocket = new Socket("127.0.0.1",5000);

			out = new ObjectOutputStream(clientSocket.getOutputStream()); 
			in = new ObjectInputStream(clientSocket.getInputStream());
			
			out.writeInt(n);			
			out.flush();

			out.writeObject(A);			
			out.flush();

			out.writeObject(B);
			out.flush();			

			C = new int[n][n];
			C = (int[][])in.readObject();

			System.out.println("*********************************");
			System.out.println("resultat est : ");
			for (int i =0 ; i<n ;  i++){
				for ( int j=0; j<n ; j++){
					System.out.print(C[i][j]+"  ");
				}
				System.out.println();
			}

			out.close();
			clientSocket.close();			
			
		 } catch (IOException e) {
			 e.printStackTrace();
		 } 
	}
}

