package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException; // 追加

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
 * Servlet implementation class SakeUpdateServlet
 */
@WebServlet("/SakeUpdate")
@MultipartConfig
public class SakeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SakeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GETリクエストはPOSTに転送
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");
		
		//リクエストパラメータの取得
		String sakeIdParam = request.getParameter("sakeId"); // 更新の場合はsakeIdも取得
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
		if (!img_name.isEmpty()) {
		    part.write(path + File.separator + img_name);
		}
		
		//遷移先URLの設定
		String url = "sakeUpdateConfirm.jsp";
		
		/* validation Check */
		String errorLog = "";
		boolean validationFlag = true;
		
		// 酒の説明のバリデーション
		if (sakeExplanation != null && sakeExplanation.length() > 50) { // ★修正点: nullチェックを追加
		    errorLog += "お酒の説明は50文字以下で入力してください。<br>";
		    validationFlag = false;
		}

		// その他の必須項目チェック (仮)
		if (sakeIdParam == null || sakeIdParam.isEmpty()) { // 更新対象IDは必須
		    errorLog += "更新する酒が選択されていません。<br>";
		    validationFlag = false;
		}
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
		// 画像は必須ではないが、もし必須であれば img_name.isEmpty() でチェック

		// エラーがあった場合、エラーメッセージを設定してフォーム画面に戻る
		if (!validationFlag) {
			request.setAttribute("errorLog", errorLog);
			// 更新フォームの初期化に必要なデータを再度設定
			try {
				model.dao.BreweryDAO Bdao = new model.dao.BreweryDAO();
				request.setAttribute("breweryList", Bdao.selectAll());
				model.dao.SakeDAO Sdao = new model.dao.SakeDAO();
				request.setAttribute("sakeList", Sdao.selectAll());
                // もし、現在入力されていたsakeIdParamがあれば、それを元にselectedSakeを再取得
                if (sakeIdParam != null && !sakeIdParam.isEmpty()) {
                    request.setAttribute("selectedSake", Sdao.selectById(Integer.parseInt(sakeIdParam)));
                }
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				url = "adminFailure.jsp"; 
				request.setAttribute("cause", "データベース処理中にエラーが発生しました。");
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
				return;
			}
			url = "sakeUpdate.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request,response);
			return;
		}
		
		int iSakeId = 0;
		int iBreweryId = 0;
		double dAlc = 0;
		
		//リクエストパラメータの型を合わせる
		try {
			iSakeId = Integer.parseInt(sakeIdParam);
			iBreweryId = Integer.parseInt(breweryId);
			dAlc = Double.parseDouble(alc);
			
		} catch (NumberFormatException e) {
			errorLog += "度数、酒蔵ID、または酒IDが不正な形式です。<br>";
			validationFlag = false;
			e.printStackTrace();
		}
		
		// ここでもし数値変換エラーがあれば再度エラー処理
		if (!validationFlag) {
		    request.setAttribute("errorLog", errorLog);
		    try {
                model.dao.BreweryDAO Bdao = new model.dao.BreweryDAO();
                request.setAttribute("breweryList", Bdao.selectAll());
                model.dao.SakeDAO Sdao = new model.dao.SakeDAO();
                request.setAttribute("sakeList", Sdao.selectAll());
                if (sakeIdParam != null && !sakeIdParam.isEmpty()) {
                    request.setAttribute("selectedSake", Sdao.selectById(Integer.parseInt(sakeIdParam)));
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                url = "adminFailure.jsp";
                request.setAttribute("cause", "データベース処理中にエラーが発生しました。");
            }
		    url = "sakeUpdate.jsp";
		    RequestDispatcher rd = request.getRequestDispatcher(url);
		    rd.forward(request, response);
		    return;
		}
		
		//リクエストスコープへの属性の設定
		SakeBean sake = new SakeBean();
		
		sake.setSakeId(iSakeId);
		sake.setSakeName(sakeName);
		sake.setBreweryId(iBreweryId);
		sake.setAlc(dAlc);
		sake.setfDrink(fDrink);
		sake.setTaste(taste);
		sake.setSakeExplanation(sakeExplanation);
		sake.setsImgPath(img_name); // 新しい画像がアップロードされなければ空文字のまま

		request.setAttribute("sake", sake);
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}