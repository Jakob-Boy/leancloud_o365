package hqr.o365.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import hqr.o365.dao.TaOfficeInfoRepo;
import hqr.o365.domain.TaOfficeInfo;

@Service
public class GetOfficeInfo {
	
	@Autowired
	private TaOfficeInfoRepo repo;
	
	@Value("${UA}")
    private String ua;

	@Cacheable(value="cacheOfficeInfo")
	public String getAllOfficeInfo(int intRows, int intPage) {
		long total = repo.count();
		List<TaOfficeInfo> rows = new ArrayList<TaOfficeInfo>();
		if(total>0) {
			rows = repo.getOfficeInfos(intRows * (intPage - 1), intRows );
		}
		
		HashMap map = new HashMap();
		map.put("total", total);
		map.put("rows", rows);
		
		return JSON.toJSON(map).toString();
	}
	
}
