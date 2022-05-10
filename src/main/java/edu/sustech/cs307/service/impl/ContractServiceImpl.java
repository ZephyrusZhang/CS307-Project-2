package edu.sustech.cs307.service.impl;

import edu.sustech.cs307.entity.Contract;
import edu.sustech.cs307.mapper.ContractMapper;
import edu.sustech.cs307.service.IContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements IContractService {

}
