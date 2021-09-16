package Game.domain;

import Game.data.CompanyRepository;
import Game.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository repository;
    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> findAll(){
        return repository.findAll();
    }

    public Result<Company> findById(int companyId){
        Result<Company> result = new Result<>();
        if (companyId > 0 && companyId < 27){
            Company company = repository.findById(companyId);
            result.setPayload(company);
        }else{
            result.addMessage("CompanyId needs to be between 1 and 26.", ResultType.INVALID);
        }
        return result;
    }

}
