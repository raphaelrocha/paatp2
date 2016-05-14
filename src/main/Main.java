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

import engine.ReadFile;
import models.Customer;
import models.Group;
import models.Product;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadFile rf = new ReadFile();
		rf.read("amazon-meta.txt"); //trocar pelo caminho do arquivo desejado. }
	}
}