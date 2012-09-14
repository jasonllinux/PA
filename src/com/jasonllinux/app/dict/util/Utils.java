package com.jasonllinux.app.dict.util;


	import java.io.BufferedInputStream;
	import java.io.BufferedOutputStream;
	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.io.OutputStream;
	import java.io.OutputStreamWriter;
	import java.io.UnsupportedEncodingException;
	import java.io.Writer;
	import java.util.zip.ZipEntry;
	import java.util.zip.ZipInputStream;

	import android.content.Context;
	import android.util.Log;

	public class Utils {

	        public static Utils utils;

	        public static Utils init() {
	                if (utils == null) {
	                        utils = new Utils();
	                }
	                return utils;
	        }

	        /**
	         * ¸´ÖÆ¶þ½øÖÆÎÄ¼þ
	         *
	         * @param fileFromPath
	         * @param fileToPath
	         */
	        public void copyBinFile(String fileFromPath, String fileToPath) {
	                // Èç¹ûÎÄ¼þ²»´æÔÚÔò´´½¨Ëü
	                createFileIfNotExist(fileFromPath);
	                createNewFile(fileToPath);

	                InputStream in = null;
	                OutputStream out = null;

	                try {
	                        in = new FileInputStream(fileFromPath);
	                        out = new FileOutputStream(fileToPath);
	                } catch (FileNotFoundException e) {
	                        e.printStackTrace();
	                }

	                this.copyBinFile(in, out);
	        }

	        public void copyBinFile(InputStream in, OutputStream out) {
	                // ¿ªÊ¼¸´ÖÆÎÄ¼þ
	                InputStream inBuffer = null;
	                OutputStream outBuffer = null;

	                inBuffer = new BufferedInputStream(in);
	                outBuffer = new BufferedOutputStream(out);

	                int byteData = 0;
	                try {
	                        while (true) {
	                                byteData = inBuffer.read();
	                                if (byteData == -1)
	                                        break;
	                                out.write(byteData);
	                        }
	                } catch (IOException e) {
	                        e.printStackTrace();
	                } finally {
	                        try {
	                                if (inBuffer != null)
	                                        inBuffer.close();
	                                if (outBuffer != null)
	                                        outBuffer.close();
	                        } catch (IOException e) {
	                                e.printStackTrace();
	                        }
	                }
	        }

	        /**
	         * ¸´ÖÆÎÄ±¾ÎÄ¼þ
	         *
	         * @param in
	         * @param targetFilePath
	         */
	        public void copyFile(InputStream in, String targetFilePath) {
	                createNewFile(targetFilePath);

	                OutputStream out = null;

	                try {
	                        out = new FileOutputStream(targetFilePath);
	                } catch (FileNotFoundException e) {
	                        e.printStackTrace();
	                }

	                this.copyFile(in, out);
	        }

	        public void copyFile(InputStream in, OutputStream out) {

	                BufferedReader br = null;
	                BufferedWriter bw = null;
	                try {
	                        br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
	                        bw = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
	                } catch (UnsupportedEncodingException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                }

	                String data = null;
	                try {
	                        while ((data = br.readLine()) != null) {
	                                bw.write(data + "\n");
	                        }
	                } catch (IOException e) {
	                        e.printStackTrace();
	                } finally {
	                        try {
	                                br.close();
	                                bw.close();
	                        } catch (IOException e) {
	                                e.printStackTrace();
	                        }
	                }
	        }

	        /**
	         * É¾³ý¾ÉµÄÎÄ¼þ£¬´´½¨ÐÂµÄ¿Õ°×µÄÎÄ¼þ¡£
	         *
	         * @param targetFilePath
	         */
	        public void createNewFile(String targetFilePath) {
	                createFileIfNotExist(targetFilePath);

	                // É¾³ýÒÑÓÐµÄÄ¿±êÎÄ¼þ
	                File targetFile = new File(targetFilePath);
	                targetFile.delete();
	                try {
	                        targetFile.createNewFile();
	                } catch (IOException e) {
	                        e.printStackTrace();
	                }
	        }

	        /**
	         * Èç¹ûÎÄ¼þ²»´æÔÚÔò´´½¨Ëü
	         *
	         * @param path
	         * @return
	         */
	        public File createFileIfNotExist(String path) {
	                File file = new File(path);
	                if (!file.exists()) {
	                        // Log.i("srcFile file exists", String.valueOf(file.exists()));
	                        try {
	                                new File(path.substring(0, path.lastIndexOf(File.separator))).mkdirs();
	                                file.createNewFile();
	                        } catch (IOException e) {
	                                e.printStackTrace();
	                        }
	                }
	                // Log.i("file create finished", String.valueOf(file.exists()));
	                return file;
	        }
	        
	        public boolean isExist(String path)
	        {
	            File file = new File(path);
	            if (file.exists()) return true;
	            else return false;
	        }
	        /**
	         * ¸´ÖÆ·ÇÎÄ±¾ÎÄ¼þ
	         * 
	         */
	 	   public void copyDictFile(InputStream inStream, String newPath) { 
		       try { 
		    	   
	               createNewFile(newPath);
		           int bytesum = 0; 
		           int byteread = 0; 

		               FileOutputStream fs = new FileOutputStream(newPath); 
		               byte[] buffer = new byte[1444]; 
	                   Log.e("copyDictFile", newPath);
		               while ( (byteread = inStream.read(buffer)) != -1) {
		            	   
		                   bytesum += byteread; //×Ö½ÚÊý ÎÄ¼þ´óÐ¡ 
		                   fs.write(buffer, 0, byteread); 
		               } 
	                   Log.e("copyDictFile", "copyDictFile2");
		               inStream.close(); 
		          
		       } 
		       catch (Exception e) { 
	               Log.e("Exception", "Exception");
		           System.out.println("¸´ÖÆµ¥¸öÎÄ¼þ²Ù×÷³ö´í"); 
		           e.printStackTrace(); 

		       } 

		   } 
	 	   
	 	    public void copyDictUTFFile(InputStream inStream,String filePath) throws UnsupportedEncodingException, FileNotFoundException {
		       try { 
		    	   
	               createNewFile(filePath);
		           int bytesum = 0; 
		           int byteread = 0; 

		               FileOutputStream fs = new FileOutputStream(filePath); 
		               byte[] buffer = new byte[512]; 
	                   Log.e("copyDictUTFFile", "copyDictUTFFile");
		               while ( (byteread = inStream.read(buffer)) != -1) {
		            	   
		                   Log.e("copyDictUTFFile", "read");
		                   bytesum += byteread; //×Ö½ÚÊý ÎÄ¼þ´óÐ¡ 
		                   fs.write(buffer, 0, byteread); 
		               } 
	                   Log.e("copyDictUTFFile", "copyDictFile2");
		               inStream.close(); 
		          
		       } 
		       catch (Exception e) { 
	               Log.e("Exception", "Exception");
		           System.out.println("¸´ÖÆµ¥¸öÎÄ¼þ²Ù×÷³ö´í"); 
		           e.printStackTrace(); 

		       } 

	}
	 	  
	 	    
	 	    public  void merge(String[] fileNames, String TargetFileName,Context mContext)
	 		throws Exception {
	 	    	
	        createNewFile(TargetFileName);
//	 		File fin = null;
//	 		// ¹¹½¨ÎÄ¼þÊä³öÁ÷
	        
	    	try {
	 		File fout = new File(TargetFileName);
	 		FileOutputStream out = new FileOutputStream(fout);
	 		for (int i = 0; i < fileNames.length; i++) {
	 			// ´ò¿ªÎÄ¼þÊäÈëÁ÷
//	 			fin = new File(fileNames[i]);
//	 			FileInputStream in = new FileInputStream(fin);
	 			// ´ÓÊäÈëÁ÷ÖÐ¶ÁÈ¡Êý¾Ý£¬²¢Ð´Èëµ½ÎÄ¼þÊý³öÁ÷ÖÐ
	 			InputStream in = mContext.getAssets().open(fileNames[i]);
	 			int c;
	 			byte[] b=new byte[1024*100];
	 			while ((c = in.read(b)) != -1) {
	 				out.write(b, 0, c);
	 			}
	 			in.close();
	 		}
	 		out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	}
	 	    
	 	    
	 	    public void copyDictUTFFile2(InputStream inStream,String filePath) {
	 	    	
	            createNewFile(filePath);

	 	        BufferedReader br = null;
	 	        StringBuffer sb = new StringBuffer();


	 	        try {
	 	                br = new BufferedReader(new InputStreamReader(inStream,"UTF-8"));
	 	                String line;
	 	                while ((line = br.readLine()) != null) {
	 	                	
	 	                        sb.append(line + "\n");
	 	                }
	 	                
	 	                
	 	                File file2 = new File(filePath); 
	 	                Writer writer = null;
	 	        		try {
	 	        			writer = new OutputStreamWriter(new FileOutputStream(file2), "UTF-8");
	 	        		} catch (UnsupportedEncodingException e1) {
	 	        			// TODO Auto-generated catch block
	 	        			e1.printStackTrace();
	 	        		} catch (FileNotFoundException e1) {
	 	        			// TODO Auto-generated catch block
	 	        			e1.printStackTrace();
	 	        		} 
	 	        		
	 	              String s3=sb.toString();
	 	        		
	 	                try {
	 	        			writer.write(s3);
	 	        		} catch (IOException e) {
	 	        			// TODO Auto-generated catch block
	 	        			e.printStackTrace();
	 	        		} 
	 	                try {
	 	        			writer.close();
	 	        		} catch (IOException e) {
	 	        			// TODO Auto-generated catch block
	 	        			e.printStackTrace();
	 	        		} 
	 	             
	 	        	

	 	          
//	 	        	}
	 	//
//	 	                
//	 	                String aString=new String(sb.toString().getBytes(),"UTF-8");
//	 	                fs.write(aString.getBytes()); 
//	 	                br.close();
//	 	                fs.close();

	 	        } catch (IOException e) {
	 	                e.printStackTrace();
	 	        } finally {
	 	                try {
	 	                        br.close();
	 	                } catch (IOException e) {
	 	                        e.printStackTrace();
	 	                }
	 	        }
	 	        

	 	}
	 	    
	 	   public void Unzip(InputStream inStream, String targetDir) {
	 		   int BUFFER = 4096*25; //ÕâÀï»º³åÇøÎÒÃÇÊ¹ÓÃ4KB£¬
	 		   String strEntry; //±£´æÃ¿¸özipµÄÌõÄ¿Ãû³Æ

	 		   try {
	 		    BufferedOutputStream dest = null; //»º³åÊä³öÁ÷
	 		  //  FileInputStream fis = new FileInputStream(zipFile);
	 		    ZipInputStream zis = new ZipInputStream(new BufferedInputStream(inStream));
	 		    ZipEntry entry; //Ã¿¸özipÌõÄ¿µÄÊµÀý

	 		    while ((entry = zis.getNextEntry()) != null) {

	 		     try {
	 		       Log.i("Unzip: ","="+ entry);
	 		      int count; 
	 		      byte data[] = new byte[BUFFER];
	 		      strEntry = entry.getName();

	 		      File entryFile = new File(targetDir + strEntry);
	 		      File entryDir = new File(entryFile.getParent());
	 		      if (!entryDir.exists()) {
	 		       entryDir.mkdirs();
	 		      }

	 		      FileOutputStream fos = new FileOutputStream(entryFile);
	 		      dest = new BufferedOutputStream(fos, BUFFER);
	 		      while ((count = zis.read(data, 0, BUFFER)) != -1) {
	 		       dest.write(data, 0, count);
	 		      }
	 		      dest.flush();
	 		      dest.close();
	 		     } catch (Exception ex) {
	 		      ex.printStackTrace();
	 		     }
	 		    }
	 		    zis.close();
	 		   } catch (Exception cwj) {
	 		    cwj.printStackTrace();
	 		   }
	 		  }

	}

