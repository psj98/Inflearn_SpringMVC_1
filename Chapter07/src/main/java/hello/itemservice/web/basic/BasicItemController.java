package hello.itemservice.web.basic;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // final이 붙은 변수로 생성자를 만들어줌 => @Autowired도 해줌
public class BasicItemController {

	private final ItemRepository itemRepository;

	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "basic/items";
	}

	@GetMapping("/{itemId}")
	public String item(@PathVariable long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/item";
	}

	@GetMapping("/add")
	public String addForm() {
		return "basic/addForm";
	}

	// addForm.html에서 input 태그의 name 속성으로 값이 넘어옴
	// @PostMapping("/add")
	public String addItemV1(@RequestParam String itemName, @RequestParam int price, @RequestParam int quantity,
			Model model) {
		Item item = new Item(itemName, price, quantity);
		itemRepository.save(item);

		model.addAttribute("item", item); // 상품 상세로 이동하기 때문에 model로 item 전달

		return "basic/item";
	}

	// @ModelAttribute를 통해 Item 객체를 생성하지 않고(new) 자동으로 생성
	// Model에 @ModelAttribute에 지정한 이름으로 넣어줌
	// @PostMapping("/add")
	public String addItemV2(@ModelAttribute("item") Item item, Model model) {
		itemRepository.save(item);

		// model.addAttribute("item", item);

		return "basic/item";
	}

	/**
	 * @ModelAttribute name 생략 가능
	 * model.addAttribute(item); 자동 추가, 생략 가능
	 * 생략 시 model에 저장되는 name은 클래스명 첫글자만 소문자로 등록 Item -> item
	 */
	// @PostMapping("/add")
	public String addItemV3(@ModelAttribute Item item) {
		itemRepository.save(item);
		return "basic/item";
	}
	
	/**
	 * @ModelAttribute 자체 생략 가능
	 * model.addAttribute(item) 자동 추가
	 */
	// @PostMapping("/add")
	public String addItemV4(Item item) {
		itemRepository.save(item);
		return "basic/item";
	}
	
	/**
	 * PRG - Post/Redirect/Get
	 */
	// @PostMapping("/add")
	public String addItemV5(Item item) {
		itemRepository.save(item);
		return "redirect:/basic/items/" + item.getId();
	}
	
	/**
	 * RedirectAttributes
	 */
	@PostMapping("/add")
	public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
		Item savedItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", savedItem.getId());
		redirectAttributes.addAttribute("status", true);

		return "redirect:/basic/items/{itemId}";
	}
	
	@GetMapping("{itemId}/edit")
	public String editForm(@PathVariable Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);

		return "basic/editForm";
	}
	
	@PostMapping("{itemId}/edit")
	public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
		itemRepository.update(itemId, item);
		return "redirect:/basic/items/{itemId}";
	}

	/**
	 * 테스트용 데이터 추가
	 */
	@PostConstruct
	public void init() {
		itemRepository.save(new Item("itemA", 10000, 10));
		itemRepository.save(new Item("itemB", 20000, 20));
	}
}
