import { Stage } from './Stage';

export type TeamRankingDetails = {
  rank: number;
  team: {
    id: string;
    name: string;
  };
  stage: Stage;
  score: number;
};
