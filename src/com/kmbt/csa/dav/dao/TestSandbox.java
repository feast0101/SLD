package com.kmbt.csa.dav.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TestSandbox {
  public int a() {
    System.out.println("A");
    return i;
  }

  public int b() {
    System.out.println("B");
    // throw new RuntimeException();
    return i;

  }

  public int c() {
    System.out.println("C");
    return a() + b();
  }

  private int i;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str;

    System.out.println("Enter lines of text.");
    System.out.println("Enter 'stop' to quit.");
    do {
      str = br.readLine();
      StringTokenizer st = new StringTokenizer(str);
      while (st.hasMoreElements())
        System.out.println(str = st.nextToken());
    } while (!str.equals("stop"));
    TestSandbox tsbx = new TestSandbox();
    tsbx.i = 10;
    try {
      System.out.println(tsbx.c());

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Caught" + e);
    } finally {
      System.out.println("Finally!!");
    }
    System.out.println("After..");
  }
}
