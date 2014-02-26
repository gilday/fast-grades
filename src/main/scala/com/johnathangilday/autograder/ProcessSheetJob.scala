package com.johnathangilday.autograder

import com.johnathangilday.autograder.domain.Answer
import com.johnathangilday.autograder.exceptions.AppException
import java.io.InputStream
import org.opencv.highgui.Highgui
import org.opencv.core.{Core, Size, Scalar, Mat}
import scala.io.Source
import org.opencv.imgproc.Imgproc

class ProcessSheetJob(path: String) {

  val tmp = System.getProperty("java.io.tmpdir")

  def process: Seq[Answer] = {
    val mat = Highgui.imread(path)
    preprocess(mat)
    //findLines(mat)
    Highgui.imwrite(tmp + "/debug.jpg", mat)




    throw new AppException("Not yet implemented")
  }

  /**
   * 1. Gets rid of noisy dots
   * 2. Use adaptive threshold to flip each pixel to black or white
   * operates in place on the given img
   */
  private def preprocess(img: Mat): Unit = {
    val size = new Size(3, 3)
    Imgproc.GaussianBlur(img, img, size, 0)
    Imgproc.adaptiveThreshold(img, img, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 75, 10)
    Core.bitwise_not(img, img)
  }

  /**
   *  cv::Mat img2;
  cvtColor(img,img2, CV_GRAY2RGB);
  vector<Vec4i> lines;
  HoughLinesP(img, lines, 1, CV_PI/180, 80, 400, 10);
  for( size_t i = 0; i < lines.size(); i++ )
  {
   Vec4i l = lines[i];
   line( img2, Point(l[0], l[1]), Point(l[2], l[3]), Scalar(0,0,255), 3, CV_AA);
  }
   * @param img
   */
  private def findLines(img: Mat): Unit = {
    var img2: Mat = null // new Mat(img.size(), img.`type`())
    Imgproc.cvtColor(img, img2, Imgproc.COLOR_GRAY2BGR)
    var lines: Mat = null
    Imgproc.HoughLinesP(img, lines, 1, Math.PI / 180, 80, 400, 10)
    println(String.format("HoughLinesP: %s" + lines))
  }
}
