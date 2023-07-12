import { Phase } from "./phase.model";

export interface Project {
  projectId: string;
  projectName: string;
  projectDescription: string;
  sponsor: string;
  domain: string;
  nature: string;
  startDate: Date;
  endDate: Date;
  phases: Phase[]; 

}