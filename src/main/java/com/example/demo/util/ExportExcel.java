//package com.example.demo.util;
//
//import com.jdz.common.ServerResponse;
//import org.apache.commons.io.FileUtils;
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.ClientAnchor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import sun.misc.BASE64Encoder;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URLEncoder;
//import java.text.SimpleDateFormat;
//import java.util.Collection;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///****
// *
// * @author yinzhfu
// * @category excel导出公共类
// * @param <T>
// * @serial 2017-12-12
// */
//public  class ExportExcel<T> {
//
//    public void exportExcelCar(String[] headers, String sheetName, Collection<T> dataset, OutputStream out) {
//        exportExcel(sheetName, headers, dataset, out, "yyyy-MM-dd");
//    }
//
//    /**
//     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
//     *
//     * @param title
//     *            表格标题名
//     * @param headers
//     *            表格属性列名数组
//     * @param dataset
//     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
//     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
//     * @param out
//     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
//     * @param pattern
//     *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
//     */
//    @SuppressWarnings({ "unchecked", "deprecation", "resource" })
//    public void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
//        // 声明一个工作薄
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        // 生成一个表格
//        HSSFSheet sheet = workbook.createSheet(title);
//        // 设置表格默认列宽度为15个字节
//        sheet.setDefaultColumnWidth((short) 20);
//        // 生成一个样式
//        HSSFCellStyle style = workbook.createCellStyle();
//        // 设置这些样式
//        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        // 生成一个字体
//        HSSFFont font = workbook.createFont();
//        font.setColor(HSSFColor.VIOLET.index);
//        font.setFontHeightInPoints((short) 12);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        // 把字体应用到当前的样式
//        style.setFont(font);
//        // 生成并设置另一个样式
//        HSSFCellStyle style2 = workbook.createCellStyle();
//        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
//        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        // 生成另一个字体
//        HSSFFont font2 = workbook.createFont();
//        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        // 把字体应用到当前的样式
//        style2.setFont(font2);
//
//        // 声明一个画图的顶级管理器
//        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
//        // 定义注释的大小和位置,详见文档
//        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
//        // 设置注释内容
//        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
//        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
//        comment.setAuthor("leno");
//
//        // 产生表格标题行
//        HSSFRow row = sheet.createRow(0);
//        for (short i = 0; i < headers.length; i++) {
//            HSSFCell cell = row.createCell(i);
//            cell.setCellStyle(style);
//            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
//            cell.setCellValue(text);
//        }
//
//        // 遍历集合数据，产生数据行
//        Iterator<T> it = dataset.iterator();
//        int index = 0;
//        while (it.hasNext()) {
//            index++;
//            row = sheet.createRow(index);
//            T t = (T) it.next();
//            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
//            Field[] fields = t.getClass().getDeclaredFields();
//            for (short i = 0; i < fields.length; i++) {
//                HSSFCell cell = row.createCell(i);
//                cell.setCellStyle(style2);
//                Field field = fields[i];
//                String fieldName = field.getName();
//                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
//                try {
//                    Class tCls = t.getClass();
//                    Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
//                    Object value = getMethod.invoke(t, new Object[] {});
//                    // 判断值的类型后进行强制类型转换
//                    String textValue = null;
//                    if (value instanceof Date) {
//                        Date date = (Date) value;
//                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//                        textValue = sdf.format(date);
//                    } else if (value instanceof byte[]) {
//                        // 有图片时，设置行高为60px;
//                        row.setHeightInPoints(60);
//                        // 设置图片所在列宽度为80px,注意这里单位的一个换算
//                        sheet.setColumnWidth(i, (short) (35.7 * 80));
//                        // sheet.autoSizeColumn(i);
//                        byte[] bsValue = (byte[]) value;
//                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6,
//                                index);
//                        anchor.setAnchorType(ClientAnchor.AnchorType.byId(2));  //
//                        patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
//                    } else {
//                        // 其它数据类型都当作字符串简单处理
//                        if (null == value) {
//                            textValue = "";
//                        } else {
//                            textValue = value.toString();
//                        }
//                    }
//                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
//                    if (textValue != null) {
//                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
//                        Matcher matcher = p.matcher(textValue);
//                        if (matcher.matches()) {
//                            // 是数字当作double处理
//                            cell.setCellValue(Double.parseDouble(textValue));
//                        } else {
//                            HSSFRichTextString richString = new HSSFRichTextString(textValue);
//                            HSSFFont font3 = workbook.createFont();
//                            font3.setColor(HSSFColor.BLUE.index);
//                            richString.applyFont(font3);
//                            cell.setCellValue(richString);
//                        }
//                    }
//                } catch (SecurityException e) {
//                    e.printStackTrace();
//                } catch (NoSuchMethodException e) {
//                    e.printStackTrace();
//                } catch (IllegalArgumentException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                } finally {
//                    // 清理资源
//
//                }
//            }
//        }
//        try {
//            workbook.write(out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /***
//     * @category 下载excel文件公共方法
//     *
//     * @param path
//     *            文件所在路径
//     * @param response
//     */
//    public ServerResponse<byte[]> download(String path,String filename,HttpServletRequest request, HttpServletResponse response){
//
//
//        //创建该文件对象
//        File file = new File(path.trim(),filename.trim());
//
//
//        //如果保存文件的地址不存在，就先创建目录
//        if(!file.exists()){
//            return ServerResponse.createByErrorMessage("目录不存在");
//        }
//
////        // 取得文件名。
////        String filename = file.getName();
//
//        //对文件名编码，防止中文文件乱码
//        try {
//            filename = this.getFilename(request,filename);
//            //设置响应头
//            HttpHeaders headers = new HttpHeaders();
//            //通知浏览器以下载的方式打开文件
//            headers.setContentDispositionFormData("attachment", filename);
//            //定义以流的形式下载返回文件数据
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            //使用Spring MVC框架的ResponseEntity对象封装返回下载数据
//
////            String base = new BASE64Encoder().encode(FileUtils.readFileToByteArray(file));
//
//            return ServerResponse.createBySuccess("导出成功",FileUtils.readFileToByteArray(file));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ServerResponse.createByErrorMessage("异常退出");
//        }
//
//    }
//
//
//    /**
//     * 根据浏览器的不同进行编码设置返回编码后的文件名
//     */
//    public static String getFilename(HttpServletRequest request, String filename) throws Exception{
//        //IE不通版本User-Agent中出现的关键词
//        String[] IEBrowserKeyWords = {"MSIE","Trident","Edge"};
//        //获取请求头代理信息
//        String userAgent = request.getHeader("User-Agent");
//        for(String keyWord : IEBrowserKeyWords){
//            if(userAgent.contains(keyWord)){
//                //IE内核浏览器，统一为UTF-8编码显示
//                return URLEncoder.encode(filename,"UTF-8");
//            }
//        }
//        //火狐等其他浏览器统一为ISO-8859-1编码显示
//        return new String(filename.getBytes("UTF-8"),"ISO-8859-1");
//    }
//}