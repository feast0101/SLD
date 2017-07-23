package com.kmbt.csa.dav.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class OracleSandbox {
	private int prefServCount = 0;
	private int notPrefServCount = 0;

	private void serviceCount(int[][] param) {

		int threshold = 0;
		int timer = 0;
		for (int[] valArr : param) {
			threshold = valArr[0] + valArr[2];
			if (valArr[3] == 1) {
				if ((timer + valArr[1]) <= threshold) {
					prefServCount++;
					timer = timer + valArr[1];
				}
			} else if (valArr[3] == 0) {
				if ((timer + valArr[1]) <= threshold) {
					notPrefServCount++;
					timer = timer + valArr[1];
				}
			}
		}
		System.out.println(prefServCount + " " + notPrefServCount);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		int n = 0;
		System.out.println("Enter lines of text.");
		System.out.println("Enter 'stop' to quit.");
		do {
			str = br.readLine();
			n = Integer.parseInt(str);
			int[][] param = new int[n][4];
			for (int i = 0; i < n; i++) {
				str = br.readLine();
				StringTokenizer st = new StringTokenizer(str);
				int j = 0;
				while (st.hasMoreElements()) {
					str = st.nextToken();
					param[i][j] = Integer.parseInt(str);
					// System.out.println(str);
					j++;
				}
			}
			Arrays.sort(param, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return Integer.compare(o2[0], o1[0]);
				}
			});
			
			//Arrays.sort(param, Comparator.comparingInt((int[] o1, int[] o2)-> Integer.compare(o2[0], o1[0]));
			for (int[] valArr : param) {
				for (int val : valArr)
					System.out.print(val + " ");
				System.out.println();
			}
			new OracleSandbox().serviceCount(param);

		} while (!str.equals("exit"));

		System.out.println("THE END..");
	}
}