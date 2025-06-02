package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BreweryDAO;
import model.entity.BreweryBean;

/**
 * Servlet implementation class BreweryRegistConfirmServlet
 */
@WebServlet("/BreweryRegistConfirm")
public class BreweryRegistConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BreweryRegistConfirmServlet() {
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

		//リクエストのエンコーディングを指定
		request.setCharacterEncoding("UTF-8");
		
		//遷移先URLの設定
		String url = "adminComplete.jsp";
		
		//リクエストパラメータの取得
		
		String breweryId          = request.getParameter("breweryId2");
		String breweryName        = request.getParameter("breweryName2");
		String breweryExplanation = request.getParameter("breweryExplanation2");
		String latitude           = request.getParameter("latitude2");
		String longitude          = request.getParameter("longitude2");
		String address            = request.getParameter("address2");
		String areaId             = request.getParameter("areaId2");
		String reservetionFlag    = request.getParameter("reservationFlag2");
		String reservationPath    = request.getParameter("reservationPath2");
		String bImgPath           = request.getParameter("bImgPath2");
		
		
		//一応セットしとく（完了画面は同じだから使わないと思う）
		String img_name          = request.getParameter("img_name");
		request.setAttribute("img_name", img_name);
		
		int iBreweryId = 0;
		double dLatitude = 0;
		double dLongitude = 0;
		int iAreaId = 0;
		boolean bReservetionFlag = false;
		
		if(reservetionFlag.equals("true")){
			bReservetionFlag = true; 
		}
		
		
		//リクエストパラメータの型を合わせる
		try {
			
			iBreweryId = Integer.parseInt(breweryId);
			dLatitude = Double.parseDouble(latitude);
			dLongitude = Double.parseDouble(longitude);
			iAreaId = Integer.parseInt(areaId);
			
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			
		}
		
		System.out.println(bReservetionFlag);
		
		//breweryオブジェクトを生成、値を代入
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
		
		
		//daoの成功を判断する変数
		int processingNumber = 0;
		
		//DAOクラスの生成
		BreweryDAO dao = new BreweryDAO();
	
		try {

			processingNumber = dao.insert(brewery);	

		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
		//遷移先の変更
		if(processingNumber == 0) {//失敗した時
			url = "adminFailure.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request,response);
	}
}
