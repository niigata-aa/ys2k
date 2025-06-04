package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.entity.SakeBean;

/**
 * Servlet implementation class SakeRegistServlet
 */
@WebServlet("/SakeRegist")
@MultipartConfig
public class SakeRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SakeRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GETリクエストはPOSTに転送
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");
		
		//リクエストパラメータの取得
		String sakeName = request.getParameter("sakeName");
		String breweryId = request.getParameter("breweryId");
		String alc = request.getParameter("alc");		
		String fDrink = request.getParameter("fDrink");
		String taste = request.getParameter("taste");
		String sakeExplanation = request.getParameter("sakeExplanation");
		
		//画像のファイル名取得
		Part part = request.getPart("sImgPath");
		String sImgPath = Paths.get(part.getSubmittedFileName()).getFileName().toString();
		String img_name = sImgPath.isEmpty() ? "" : sImgPath;
		
		// 画像アップロード
		String path = getServletContext().getRealPath("/img");
		// ファイルが選択されている場合のみ保存を試みる
		if (!img_name.isEmpty()) {
		    part.write(path + File.separator + img_name);
		}
		
		//遷移先URLの設定
		String url = "sakeRegistConfirm.jsp";
		
		/* validation Check */
		String errorLog = "";
		boolean validationFlag = true;
		
		// 酒の説明のバリデーション
		// 0文字の場合に警告が出ないのは、おそらくこのnullまたは空文字のチェックが原因
		// 50文字以下で打ったのに警告が出るのは、この条件式が問題
		if (sakeExplanation != null && sakeExplanation.length() > 50) { // ★修正点: nullチェックを追加
		    errorLog += "お酒の説明は50文字以下で入力してください。<br>";
		    validationFlag = false;
		}

		// その他の必須項目チェック (仮)
		if (sakeName == null || sakeName.isEmpty()) {
		    errorLog += "酒の名前は必須です。<br>";
		    validationFlag = false;
		}
		if (breweryId == null || breweryId.isEmpty()) {
		    errorLog += "酒蔵の選択は必須です。<br>";
		    validationFlag = false;
		}
		if (alc == null || alc.isEmpty()) {
		    errorLog += "度数の選択は必須です。<br>";
		    validationFlag = false;
		}
		if (fDrink == null || fDrink.isEmpty()) {
		    errorLog += "飲み方の選択は必須です。<br>";
		    validationFlag = false;
		}
		if (taste == null || taste.isEmpty()) {
		    errorLog += "味わいの選択は必須です。<br>";
		    validationFlag = false;
		}
		if (img_name.isEmpty()) { // 画像は必須と仮定
		    errorLog += "酒の写真は必須です。<br>";
		    validationFlag = false;
		}
		
		// エラーがあった場合、エラーメッセージを設定してフォーム画面に戻る
		if (!validationFlag) {
			request.setAttribute("errorLog", errorLog);
			// 登録フォームの初期化に必要なデータを再度設定
			try {
				model.dao.BreweryDAO Bdao = new model.dao.BreweryDAO();
				request.setAttribute("breweryList", Bdao.selectAll());
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				url = "adminFailure.jsp"; // データベースエラーの場合の遷移先
				request.setAttribute("cause", "酒蔵情報の取得中にエラーが発生しました。");
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
				return;
			}
			url = "sakeRegistration.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request,response);
			return; // 処理を中断
		}
		/* */
		int iBreweryId = 0;
		double dAlc = 0;
		
		//リクエストパラメータの型を合わせる
		try {
			// iSakeId = Integer.parseInt(sakeId); // SakeIdはオートインクリメントなのでパースは不要
			iBreweryId = Integer.parseInt(breweryId);
			dAlc = Double.parseDouble(alc);
			
		} catch (NumberFormatException e) {
			// 数値変換エラーもバリデーションに含めるべき
			errorLog += "度数や酒蔵IDが不正な形式です。<br>";
			validationFlag = false; // エラーフラグを立てる
			e.printStackTrace();
		}
		
		// ここでもし数値変換エラーがあれば再度エラー処理
		if (!validationFlag) {
		    request.setAttribute("errorLog", errorLog);
		    try {
                model.dao.BreweryDAO Bdao = new model.dao.BreweryDAO();
                request.setAttribute("breweryList", Bdao.selectAll());
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                url = "adminFailure.jsp";
                request.setAttribute("cause", "酒蔵情報の取得中にエラーが発生しました。");
            }
		    url = "sakeRegistration.jsp";
		    RequestDispatcher rd = request.getRequestDispatcher(url);
		    rd.forward(request, response);
		    return;
		}
		
		//リクエストスコープへの属性の設定
		SakeBean sake = new SakeBean();
		
		sake.setSakeName(sakeName);
		sake.setBreweryId(iBreweryId);
		sake.setAlc(dAlc);
		sake.setfDrink(fDrink);
		sake.setTaste(taste);
		sake.setSakeExplanation(sakeExplanation);
		sake.setsImgPath(img_name);
		
		request.setAttribute("sake", sake);
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}