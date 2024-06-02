export type TeamRanking = {
  id: number;
  rank: number;
  team: {
    id: string;
    name: string;
  };
  totalScore: number;
};
