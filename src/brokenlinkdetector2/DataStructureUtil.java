/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokenlinkdetector2;

import java.io.FileWriter;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 *
 * @author nitishchandra
 */
public class DataStructureUtil  {
private static final int MAX_PAGES_TO_SEARCH = 10;
   public static void addElement(List<Callable<Consumer>> s,DataStructure d,FileWriter f)
   {
       int count=0; 
    while(d.getsizeofURl()>0&&s.size()<MAX_PAGES_TO_SEARCH)
    {
        Consumer tobeadded = new Consumer(d.getURL(),d,f);
        if(!containsString(s,tobeadded)){
            s.add(tobeadded);
            System.out.println("Url added is "+ tobeadded.s);
        }
       
    }}
   
   public static boolean containsString(List<Callable<Consumer>>s,Consumer obj)
   {
       if(obj==null)
           return false;
      for(int i=0;i<s.size();i++)
      {
          Consumer add = new Consumer((Consumer) s.get(i));
          if(add.equals(obj))
          {
              return true;
              
          }
         
      }
      return false;
   }
   
}
