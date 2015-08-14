package assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HugeIntegerRESTApi {
	@Autowired
	HugeIntegerOperations operation;

	public HugeIntegerRESTApi() {
	}

	@RequestMapping(value = "add")
	public String add(@RequestParam(value = "first") String first, @RequestParam(value = "second") String second) {
		return operation.add(first, second);
	}

	@RequestMapping(value = "subtract")
	public String sub(@RequestParam(value = "first") String first,
			@RequestParam(value = "second") String second) {
		return operation.subtract(first, second);
	}

	@RequestMapping("/isZero")
	public boolean isZero(@RequestParam(value = "number") String first) {
		return operation.equals(first);

	}

	@RequestMapping("/operation")
	public boolean operations(@RequestParam(value = "first") String first,
			@RequestParam(value = "operator") String operator,
			@RequestParam(value = "second", defaultValue = "") String second) {
		return operation.operations(first, second, operator);
	}
}
