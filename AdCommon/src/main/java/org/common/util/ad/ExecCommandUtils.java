package org.common.util.ad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
 * 功能概述：<br>
 *
 */
public class ExecCommandUtils {

  private final static Logger LOG = Logger.getLogger(ExecCommandUtils.class);

  /**
   * 执行外部命令
   * 
   * @param command
   */
  public static void execCommand(final String command, String resultFilePath) {
    try {
      int exitVal = 53559;

      Process process = Runtime.getRuntime().exec(command);
      new WatchDog(process, command).start();
      BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
      BufferedReader brerr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
      String inline = null;

      File file = new File(resultFilePath);
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter fw = new FileWriter(file);
      BufferedWriter bw = new BufferedWriter(fw);
      boolean isSuccess = false;
      while (null != (inline = br.readLine())) {
        isSuccess = true;
        bw.write(inline);
        bw.newLine();
        bw.flush(); // 将数据更新至文件
      }
      if (!isSuccess) {
        while (null != (inline = brerr.readLine())) {
          bw.write(inline);
          bw.newLine();
          bw.flush(); // 将数据更新至文件
        }
      }
      LOG.debug("isSuccess:" + isSuccess);
      bw.close();
      fw.close();
      br.close();
      brerr.close();
      exitVal = process.waitFor();
      process.destroy();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 监控外部命令执行情况，
   * 
   * 
   */
  private static class WatchDog extends Thread {
    private int timeout = 20000;
    private Process target;
    private String cmd;

    WatchDog(Process process, String cmd) {
      this.target = process;
      this.cmd = cmd;
    }

    public void run() {
      final long start = System.currentTimeMillis();

      while (true) {
        if (System.currentTimeMillis() - start > timeout) {
          target.destroy();
          return;
        } else {
          try {
            target.waitFor();
            // target.destroy();
            return;
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

    }
  }
}
