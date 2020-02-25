package ${pName};

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
 
/**
 * @类说明 [${cName}]控制器
 * @author ${auth}
 * @date ${time}
 **/
@RestController
@RequestMapping("${low}")
public class ${upp}Controller {

    @Autowired
    private ${upp}Service service;//注入[${cName}]业务逻辑接口

    /**
     * @方法说明  新增[${cName}]记录
     */
    @PostMapping
    public Result add(@RequestBody @Valid ${upp} ${lowUpp}, BindingResult result) {
		if (result.hasErrors()) {
			return Result.error(1, result.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList()).toString());
		}
        return Result.success(service.save(${lowUpp}));
    }

    /**
     * @方法说明 按主键删除${cName}记录
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success( service.removeById(id) );
    }

    /**
     * @方法说明 修改${cName}记录
     */
    @PutMapping
    public Result edit(@RequestBody @Valid ${upp} ${lowUpp}, BindingResult result) {
		if (result.hasErrors()) {
			return Result.error(1, result.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList()).toString());
		}
        return Result.success(service.updateById(${lowUpp}));
    }
    /**
     * @方法说明 按条件查询分页${cName}列表
     */
    @PostMapping("/page/{current}/{size}")
    public Result page(@RequestBody ${upp} ${lowUpp}, @PathVariable long current, @PathVariable long size) {
        return Result.success(service.page(new Page<${upp}>(current,size), new QueryWrapper<${upp}>(${lowUpp})));
    }

    /**
     * @方法说明 按主键查单个${cName}记录
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        return Result.success(service.getById(id));
    }
}
