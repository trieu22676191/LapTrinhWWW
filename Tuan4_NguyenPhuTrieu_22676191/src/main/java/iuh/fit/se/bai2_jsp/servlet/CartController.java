package iuh.fit.se.bai2_jsp.servlet;

import iuh.fit.se.bai2_jsp.beans.CartBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/CartController")
public class CartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        CartBean cart = (CartBean) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartBean();
            session.setAttribute("cart", cart);
        }

        if (action != null && !action.isEmpty()) {
            if (action.equalsIgnoreCase("add")) {
                addToCart(request, cart);
            } else if (action.equalsIgnoreCase("update")) {
                updateCart(request, cart);
            }
        }
        response.sendRedirect("ShoppingCart.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        CartBean cart = (CartBean) session.getAttribute("cart");

        if (action != null && !action.isEmpty()) {
            if (action.equalsIgnoreCase("delete")) {
                deleteCart(request, cart);
            }
        }
        response.sendRedirect("ShoppingCart.jsp");
    }

    private void addToCart(HttpServletRequest request, CartBean cart) {
        String strModelNo = request.getParameter("modelNo");
        String strDescription = request.getParameter("description");
        String strPrice = request.getParameter("price");
        String strQuantity = request.getParameter("quantity");

        cart.addCartItem(strModelNo, strDescription, strPrice, strQuantity);
    }

    private void updateCart(HttpServletRequest request, CartBean cart) {
        String strItemIndex = request.getParameter("itemIndex");
        String strQuantity = request.getParameter("quantity");

        if (cart != null) {
            cart.updateCartItem(strItemIndex, strQuantity);
        }
    }

    private void deleteCart(HttpServletRequest request, CartBean cart) {
        String strItemIndex = request.getParameter("itemIndex");
        if (cart != null) {
            cart.deleteCartItem(strItemIndex);
        }
    }
}