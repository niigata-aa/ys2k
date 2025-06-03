package servlet;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.entity.BreweryBean;

/**
 * Servlet implementation class BreweryUpdateServlet
 */
@WebServlet("/BreweryUpdate")
@MultipartConfig
public class BreweryUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BreweryUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//リクエストのエンコーディングを指定
		request.setCharacterEncoding("UTF-8");
		
		//リクエストパラメータの取得
		String breweryId          = request.getParameter("breweryId");
		String breweryName        = request.getParameter("breweryName");
		//String bImgPath           = request.getParameter("bImgPath");
		String breweryExplanation = request.getParameter("breweryExplanation");
		String latitude           = request.getParameter("latitude");
		String longitude          = request.getParameter("longitude");
		String areaId             = request.getParameter("areaId");
		String address            = request.getParameter("address");
		String reservetionFlag    = request.getParameter("reservetionFlag");
		String reservationPath    = request.getParameter("reservetionPath");
		
		//画像のファイル名取得
		//アップロードしたファイルを取得
		Part part = request.getPart("bImgPath");
		//選択したファイルの名前を取得→パス全体からファイル名だけを取り出す
		String bImgPath = Paths.get(part.getSubmittedFileName()).getFileName().toString();
		//ファイル名が空なら空文字で、違ったらそのまま
		//if(bImgPath.isEmpty)ならimg_nameが"",違うなら"bImgPath"という意味
		String img_name = bImgPath.isEmpty() ? "" : bImgPath;
		
		// 画像アップロード
		//./img配下のパスを取得
		String path = getServletContext().getRealPath("/img");
		//取得したディレクトリに画像を保存
		part.write(path + File.separator + img_name);
		
		
		//遷移先URLの設定
		String url = "breweryUpdateConfirm.jsp";
		
		/* validation Check */
		String errorLog = "";
		boolean validationFlag = true;
		/*                  */

		int iBreweryId = 0;
		double dLatitude = 0;
		double dLongitude = 0;
		int iAreaId = 0;
		boolean bReservetionFlag = false;
		
		//チェックボックスは未選択だとnullで送られる
		if(reservetionFlag == null) {
			reservetionFlag = "false";
		}
		
		if(reservetionFlag.equals("on")){
			bReservetionFlag = true; 
		}
		
		
		//リクエストパラメータの型を合わせる
		//if 値の型が合わなかった場合errorログを飛ばす
		try {
			
			iBreweryId = Integer.parseInt(breweryId);
			
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			
		}
		try {
			
			dLatitude = Double.parseDouble(latitude);

		} catch (NumberFormatException e) {
			
			//エラーメッセージの生成
			errorLog += "緯度は小数を入力してください。<br>";
			
			//遷移先の設定
			url = "BreweryUpdatePreparation";
			validationFlag = false;	
			
			e.printStackTrace();
			
		}
		try {
		
			dLongitude = Double.parseDouble(longitude);
		
		} catch (NumberFormatException e) {
			
			//エラーメッセージの生成
			errorLog += "経度は小数を入力してください。<br>";
			
			//遷移先の設定
			url = "BreweryUpdatePreparation";
			validationFlag = false;	
			
			e.printStackTrace();
			
		}
		
		try {
			
			iAreaId = Integer.parseInt(areaId);
			
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			
		}
		
		if(!validationFlag) {
			request.setAttribute("errorLog", errorLog);
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request,response);
		}
		
		//リクエストスコープへの属性の設定
		BreweryBean brewery = new BreweryBean();
		
		brewery.setBreweryId(iBreweryId);
		brewery.setBreweryName(breweryName);
		brewery.setbImgPath(bImgPath);
		brewery.setBreweryExplanation(breweryExplanation);
		brewery.setLatitude(dLatitude);
		brewery.setLongitude(dLongitude);
		brewery.setAreaId(iAreaId);
		brewery.setAddress(address);
		brewery.setReservationFlag(bReservetionFlag);
		brewery.setReservationPath(reservationPath);
		
		request.setAttribute("brewery", brewery);
		
		if(validationFlag) {
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request,response);
		}

	}

}
