package gr.codelearn.spring.showcase.app.controller.demo;

import gr.codelearn.spring.showcase.app.domain.Customer;
import gr.codelearn.spring.showcase.app.domain.CustomerCategory;
import gr.codelearn.spring.showcase.app.mapper.OrderMapper;
import gr.codelearn.spring.showcase.app.mapper.ProductMapper;
import gr.codelearn.spring.showcase.app.service.CustomerService;
import gr.codelearn.spring.showcase.app.service.OrderService;
import gr.codelearn.spring.showcase.app.service.ProductService;
import gr.codelearn.spring.showcase.app.service.demo.JokeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mvc")
public class MvcController {
	private final JokeService jokeService;
	private final ProductService productService;
	private final ProductMapper productMapper;
	private final OrderService orderService;
	private final OrderMapper orderMapper;
	private final CustomerService customerService;

	// Model variables
	private static final String JOKE = "joke";
	private static final String PRODUCT_LIST = "products";
	private static final String ORDER = "order";
	private static final String ERROR_MESSAGE = "error";
	private static final String CUSTOMER_CATEGORIES = "customerCategories";
	private static final String CUSTOMER_FORM = "customer";

	// Cookie variable
	private static final String COOKIE_NAME = "counter";

	@GetMapping
	public String index(Model model) {
		final String joke = jokeService.getSingleJoke("Programming");
		model.addAttribute(JOKE, joke);
		return "index";
	}

	@GetMapping("/about")
	public String aboutUs(HttpServletRequest request, HttpServletResponse response) {
		createCookie(request, response);
		return "about";
	}

	@GetMapping("/products")
	public String showAllOurProducts(Model model) {
		model.addAttribute(PRODUCT_LIST, productMapper.toResources(productService.findAll()));
		return "products";
	}

	@GetMapping("/findOrder")
	public String findOrder(Model model, @RequestParam(required = false) Long orderId) {
		if (orderId != null) {
			// Putting fetching into a try/catch so that our ExceptionHandler does not return an error-Json
			try {
				model.addAttribute(ORDER, orderMapper.toResource(orderService.getLazy(orderId)));
			} catch (NoSuchElementException e) {
				model.addAttribute(ERROR_MESSAGE, "The order you searched for does not exist");
			}
		}
		return "findOrder";
	}

	@GetMapping("/registerCustomer")
	public String registerCustomer(Model model) {
		// Checking if there has been a redirect from the POST request, which usually results to errors shown
		// If not, then create a new customer to match the form
		if (!model.containsAttribute(CUSTOMER_FORM)) {
			model.addAttribute(CUSTOMER_FORM, new Customer());
		}
		model.addAttribute(CUSTOMER_CATEGORIES, CustomerCategory.values());
		return "registerCustomer";
	}

	@PostMapping(value = "/registerCustomer")
	public String registerCustomer(@Valid @ModelAttribute(CUSTOMER_FORM) Customer customer,
								   BindingResult bindingResult,
								   RedirectAttributes redirectAttributes, HttpSession session) {
		if (bindingResult.hasErrors()) {
			// Add the errors as flash attributes for the GET request
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + CUSTOMER_FORM,
												 bindingResult);
			// Also add the customer with his/her fields, to not lose the data the user has written
			redirectAttributes.addFlashAttribute(CUSTOMER_FORM, customer);
		} else {
			final Customer savedCustomer = customerService.create(customer);
			session.setAttribute("registeredCustomerID", savedCustomer.getId());
		}
		return "redirect:/mvc/registerCustomer";
	}

	private void createCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie;
		final Optional<Cookie> cookieOptional = readCookie(request);
		// If cookie is present, add one to it and saved it to the response
		if (cookieOptional.isPresent()) {
			cookie = cookieOptional.get();
			String cookieValueStr = cookie.getValue();
			int cookieValue = Integer.parseInt(cookieValueStr);
			cookieValue++;
			cookieValueStr = String.valueOf(cookieValue);
			cookie.setValue(cookieValueStr);
		} else {
			cookie = new Cookie(COOKIE_NAME, "0");
			// Never expires
			cookie.setMaxAge(-1);
		}
		response.addCookie(cookie);
	}

	private Optional<Cookie> readCookie(HttpServletRequest request) {
		if (request.getCookies() != null) {
			return Arrays.stream(request.getCookies()).filter(c -> c.getName().equals(COOKIE_NAME)).findAny();
		}
		return Optional.empty();
	}

}
