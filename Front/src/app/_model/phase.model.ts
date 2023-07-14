import { Step } from "./step.model";

export interface Phase {
    phaseId: number;
    phaseName: string;
    startDate: Date;
    endDate: Date;
    project: string; 
    steps: Step[];
  }