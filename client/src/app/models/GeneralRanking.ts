import { Runner } from './Runner';
import { Stage } from './Stage';

export type GeneralRanking = {
  id: number;
  rank: number;
  runner: Runner;
  stage: Stage;
  chrono: string;
  score: number;
};
