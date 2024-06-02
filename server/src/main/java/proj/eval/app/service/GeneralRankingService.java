package proj.eval.app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import proj.eval.app.model.stat.GeneralRanking;
import proj.eval.app.repository.GeneralRankingRepository;

@Service
public class GeneralRankingService {

  private GeneralRankingRepository repository;

  public GeneralRankingService(GeneralRankingRepository repository) {
    this.repository = repository;
  }

  public List<GeneralRanking> ranking() {
    return repository.findAll();
  }
}
