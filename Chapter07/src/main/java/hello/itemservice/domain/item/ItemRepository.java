package hello.itemservice.domain.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

	private static final Map<Long, Item> store = new HashMap<>(); // 아이템 저장
	private static long sequence = 0L;

	public Item save(Item item) {
		item.setId(++sequence);
		store.put(item.getId(), item);

		return item;
	}

	// 상품 찾기 => 상세 정보
	public Item findById(Long id) {
		return store.get(id);
	}

	// 상품 목록
	public List<Item> findAll() {
		return new ArrayList<>(store.values());
	}

	// 상품 정보 수정
	public void update(Long itemId, Item updateParam) {
		Item findItem = findById(itemId);
		findItem.setItemName(updateParam.getItemName());
		findItem.setPrice(updateParam.getPrice());
		findItem.setQuantity(updateParam.getQuantity());
	}

	// 상품 초기화
	public void clearStore() {
		store.clear();
	}
}
