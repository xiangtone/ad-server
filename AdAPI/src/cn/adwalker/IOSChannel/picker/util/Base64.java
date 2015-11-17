package cn.adwalker.IOSChannel.picker.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64 {
	/**    
     * BASE64解密   
   * @param key          
     * @return          
     * @throws Exception          
     */              
    public static byte[] decryptBASE64(String key) throws Exception {               
        return (new BASE64Decoder()).decodeBuffer(key);               
    }               
                  
    /**         
     * BASE64加密   
   * @param key          
     * @return          
     * @throws Exception          
     */              
    public static String encryptBASE64(byte[] key) throws Exception {               
        return (new BASE64Encoder()).encodeBuffer(key);               
    }  
    
    public static void main(String[] args) throws Exception     
    {     
        //String data = "eyJwcmlkYXRhIjoiQmlCZE9ZbHRDVlNtNE9XazFUdEd6N3BUU3JyU2s1V1RrV3ViQnJWQ2xtSy1lRUY2UlRlX20iLCJhcHBpZCI6IjEwMDAwNTEiLCJpbWVpIjoiMDBmNGI5N2Y5OTEzIiwibWFjIjoiMDBmNGI5N2Y5OTEzIiwidHJhZGVubyI6IkU3MTYyNDk3OEZBRDI2NjBDNEY4OEE4RThCNUU4NjA5In0=";     
        String data="eyJwcmlkYXRhIjoid2FucHUiLCJhcHBpZCI6IjEwMDAwNTEiLCJpbWVpIjoiQ0Y1QzEzMTEtQzNDMS00MTkyLUFGNDItQTVGMzhBRDU3OTU0IiwibWFjIjoiQ0Y1QzEzMTEtQzNDMS00MTkyLUFGNDItQTVGMzhBRDU3OTU0IiwidHJhZGVubyI6IkZDN0M0MDA1OTIzMjBGQUNCOTBGQzQ2NzE3NjNBOUI1In0=";
             
        byte[] byteArray = Base64.decryptBASE64(data);     
        System.out.println("解密后："+new String(byteArray));     
    }
}
