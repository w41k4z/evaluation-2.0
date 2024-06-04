import { Stage } from './Stage';

export type TeamPenalty = {
  id: number;
  stage: Stage;
  team: {
    id: string;
    name: string;
  };
  penalty: string;
};
