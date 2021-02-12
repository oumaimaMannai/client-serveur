import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/*
 * www.codeurjava.com
 */

public class Serveur2 {
	
	private static int[][] multip (int n,int[][] A , int[][] B){
		int[][] C = new int[n][n];
		for (int i =0 ; i<n ;  i++){
			for ( int j=0; j<n ; j++){
				C[i][j] = 0;
				for (int k=0 ; k<n ; k++){
					C[i][j] += A[i][k]*B[k][j]; 
				}
			}
		}
		return C ;
	}


	public static void main(String[] args) throws ClassNotFoundException {
		
		final ServerSocket serveurSocket ;
		final Socket clientSocket ;
		final ObjectInputStream in;
		final ObjectOutputStream out;
		
		System.out.println("Server : ");

		try {
			 serveurSocket = new ServerSocket(5000);
			 clientSocket = serveurSocket.accept();
			 
			 System.out.println("accepted");
			 
			 in = new ObjectInputStream(clientSocket.getInputStream());
			 out = new ObjectOutputStream(clientSocket.getOutputStream());
			 
			 while (true) {

				int n;							
				n = in.readInt();

				int[][] A = new int[n][n];
				int[][] B = new int[n][n];
				int[][] C = new int[n][n];

				A = (int[][])in.readObject();	
				B = (int[][])in.readObject();
				
				C = multip(n,A,B);

				out.writeObject(C);
				

				System.out.println("A = ");
				for (int i=0; i<n;i++) {
					for(int j=0; j<n;j++) {
						System.out.print(A[i][j]+" ");
					}
					System.out.println();
				}

				System.out.println("B = ");
				for (int i=0; i<n;i++) {
					for(int j=0; j<n;j++) {
						System.out.print(B[i][j]+" ");
					}
					System.out.println();
				}
			}
			 
			}catch (IOException e) {
				e.printStackTrace();
			}
}	
}
