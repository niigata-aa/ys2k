package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストのエンコーディング方式を指定
			request.setCharacterEncoding("UTF-8");
			
			//リクエストパラメータの取得
			String sakeId = request.getParameter("sakeId");
			String sakeName = request.getParameter("sakeName");
			String breweryId = request.getParameter("breweryId");
			String alc = request.getParameter("alc");		
			String fDrink = request.getParameter("fDrink");
			String taste = request.getParameter("taste");
			String sakeExplanation = request.getParameter("sakeExplanation");
			//String sImgPath = request.getParameter("sImgPath");
			
			//画像のファイル名取得
			//アップロードしたファイルを取得
			Part part = request.getPart("sImgPath");
			//選択したファイルの名前を取得→パス全体からファイル名だけを取り出す
			String sImgPath = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			//ファイル名が空なら空文字で、違ったらそのまま
			//if(sImgPath.isEmpty)ならimg_nameが"",違うなら"sImgPath"という意味
			String img_name = sImgPath.isEmpty() ? "" : sImgPath;
			
			// 画像アップロード
			//./img配下のパスを取得
			String path = getServletContext().getRealPath("/img");
			//取得したディレクトリに画像を保存
			part.write(path + File.separator + img_name);
			
			System.out.println("アップロードされたファイル：" + sImgPath);
			System.out.println("保存先のパス：" + path + File.separator + img_name);
			System.out.println("アップロードされたファイル内容：" + part);
			System.out.println(request.getPart("sImgPath"));
			
			int iSakeId = 0;
			int iBreweryId = 0;
			double dAlc = 0;
			
			//リクエストパラメータの型を合わせる
			try {
				
				iSakeId = Integer.parseInt(sakeId);
				iBreweryId = Integer.parseInt(breweryId);
				dAlc = Double.parseDouble(alc);
				
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
				
			}
			
			System.err.println(dAlc);
			
				
			//遷移先URLの設定
			String url = "sakeUpdateConfirm.jsp";
			
			//リクエストスコープへの属性の設定
			SakeBean sake = new SakeBean();
			
			sake.setSakeId(iSakeId);
			sake.setSakeName(sakeName);
			sake.setBreweryId(iBreweryId);
			sake.setAlc(dAlc);
			sake.setfDrink(fDrink);
			sake.setTaste(taste);
			sake.setSakeExplanation(sakeExplanation);
			sake.setsImgPath(sImgPath);
			
			request.setAttribute("sake", sake);
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		
	}

}
