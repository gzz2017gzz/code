package com.gzz.createcode.mvc.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gzz.createcode.mvc.model.Field;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * @功能描述 代码生成器控制器类
 * @author gzz_gzz@163.com
 * @date 2018-02-15
 */
@RestController
@RequestMapping("/api")
public class Test {

	/**
	 * @功能描述 查询数据库中表名列表
	 * response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("" + tables + "你好吗") + ".zip");
 let fileName = decodeURI(res.headers['content-disposition'].split('=')[1]);
	 */
	@RequestMapping("/user")
	public void queryList(HttpServletResponse response) {
		try {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + new String(("中华人民共和国.xls").getBytes(), "iso8859-1"));
			response.setHeader("branchname", new String(("一地在要工上是中国同").getBytes(), "iso8859-1"));

			WritableWorkbook wbook = Workbook.createWorkbook(response.getOutputStream());
			WritableSheet sheet = wbook.createSheet("定单记录.xls", 0);
			WritableCellFormat format = new WritableCellFormat();
			format.setBorder(Border.ALL, BorderLineStyle.THIN);
			int row = 2;

			String[] headers = { "业务编号", "客户编号", "客户姓名	", "下单时间", "状态", "支付方式", "业务类型", "下单人", "收银人", "会藉/教练", "支付平台定单号", "下单平台", "应收(元)", "实收(元)", "押金(元)", "定金(元)" };
			int columnIndex = 0;
			format.setFont(new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false));
			for (String head : headers) {
				sheet.addCell(new Label(columnIndex++, row, head, format));
			}
			format = new WritableCellFormat();
			format.setBorder(Border.ALL, BorderLineStyle.THIN);

			// 设置表头信息与样式
			sheet.mergeCells(0, 1, headers.length - 1, 1);
			format = new WritableCellFormat();
			format.setFont(new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false));
			format.setAlignment(Alignment.RIGHT);
			sheet.addCell(new Label(0, 1, "导出时间:" + "  操作人:", format));
			format = new WritableCellFormat();
			format.setFont(new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false));
			format.setAlignment(Alignment.CENTRE);
			sheet.mergeCells(0, 0, headers.length - 1, 0);
			sheet.addCell(new Label(0, 0, "定单记录", format));
			wbook.write();
			if (wbook != null) {
				wbook.close();
			}
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
