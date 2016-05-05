package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.crypto.spec.GCMParameterSpec;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import models.Customer;
import models.Group;
import models.Product;

public class Main {
	
	public static void lerTexto(String nomeArquivo){ 
		HashMap<String,Customer> hmCustomers = new HashMap<String,Customer>();
		HashMap<String,Product> hmProducts = new HashMap<String,Product>();
		HashMap<String,Group> hmGroups = new HashMap<String,Group>();
		Product product = null;
		//int contaCustomer = 0;
		boolean flagProduct = false;
		int salto = 0;
		int totalAvaliacoes=0;
		String productAsin=null;
		try {
			System.out.println("Abrindo arquivo...");
			File arquivo = new File(nomeArquivo); 
			String caracteres = " #@__\\|/.*"+"  ";
			//String words[] = line.split("[" + Pattern.quote(caracteres) + "]");
			int i=0;
			FileReader arq = new FileReader(arquivo);
		    BufferedReader lerArq = new BufferedReader(arq);
		    String linha = lerArq.readLine();
		    
			//while(sc.hasNext()){
		    
		    System.out.println("Lendo arquivo...");
			while(linha != null){
				//MARCA O INICIO DE UM PRODUTO
				if(linha.length()==0 && flagProduct == false){
					flagProduct=true;
					//IMPEDE QUE ENTRE NO ULTIMO IF NO MESMO LOOP QUE ENTROU NEST IF
					salto = 1;
					//INSTANCIA UM OBJETO DO TIPO PRODUTO QUE SERÁ PREENCHIDO COM OS DADOS
					//COLETADOS NAS LINHES SEGUINTES
					product = new Product();
				}
				
				//PREENCHE O OBJETO PRODUTO
				if(linha.length()>0 && flagProduct == true){
					if(linha.contains("ASIN:")){
						//COLETA O ID DO PRO
						linha = linha.replace(" ", "");
						linha = linha.replace("ASIN:", "");
						product.setAsin(linha);
						productAsin = linha;
						//System.out.println(linha);
					}
					
					if(linha.contains("title:")){
						linha = linha.replace("title: ", "");
						product.setTitle(linha);
					}
					
					if(linha.contains("group:")){
						linha = linha.replace("group: ", "");
						if(!hmGroups.containsKey(linha)){
							Group group = new Group();
							group.setName(linha);
							hmGroups.put(linha, group);
						}
						product.setGroup(hmGroups.get(linha));
					}
					
					if(linha.contains("discontinued")){
						linha = linha.replace(" product", "");
						linha = linha.replace(" ", "");
						if(!hmGroups.containsKey(linha)){
							Group group = new Group();
							group.setName(linha);
							hmGroups.put(linha, group);
						}
						product.setGroup(hmGroups.get(linha));
					}
					
					if(linha.contains("cutomer: ")){
						int a= linha.indexOf("cutomer: ");
						int b = linha.indexOf("  rating:");
						
						String idCustomer = linha.substring(a+9,b).replace(" ","");
						
						Customer customer = new Customer();
						customer.setId(idCustomer);
						hmCustomers.put(idCustomer, customer);
						product.getListCustomer().put(idCustomer, customer);
						totalAvaliacoes++;
					}
				}
				
				//produto acabou
				if(linha.length()==0 && flagProduct == true && salto == 0){
					hmProducts.put(productAsin, product);
					product = null;
					flagProduct=false;
				}
				
				salto=0;//agora vai entrar no ultimo if no proximo loop
								
				linha = lerArq.readLine();
			} 
			
			arq.close(); 
		} catch (FileNotFoundException e) { 
			e.printStackTrace(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//ESSE BLOCO IMPRIME O CONETÚDO DAS CLASSES
		/*ArrayList<Product> arrProducts = new ArrayList<Product>(hmProducts.values()); 
		for(int i=0;i<arrProducts.size();i++){
			System.out.println("Produto: "+arrProducts.get(i).getAsin());
			System.out.println(" Grupo: "+arrProducts.get(i).getGroup().getName());
			for(int j=0;j<arrProducts.get(i).getArrayListCustomer().size();j++){
				System.out.println("   Cliente: "+arrProducts.get(i).getArrayListCustomer().get(j).getId());
			}
		}*/
		System.out.println("Extraindo dados...");
		System.out.println("Total de produtos salvos: "+hmProducts.size());
		System.out.println("Total de clientes salvos: "+hmCustomers.size());
		System.out.println("Total de grupos de produtos salvos: "+hmGroups.size());
		System.out.println("Total de avaliacoes: "+totalAvaliacoes);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		lerTexto("amazon-meta.txt"); //trocar pelo caminho do arquivo desejado. }
	}

}
