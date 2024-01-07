import React, {useEffect , useState} from 'react'
import Sidebar from '../Sidebar/Sidebar'
import axios from 'axios';
import './index.css'
function EmployerDashboard() {
  const employerMenuItems = [
    { label: 'Dashboard', link: '/employer/dashboard' },
    { label: 'View Applicants', link: '/applicants' },
    { label: 'Create Job Postings', link: '/create-job' },
  ]
  const[jobs, setJobs] = useState([]);
  useEffect(() => {
    const fetchJobs = async () => {
      try {
        const response = await axios.get("http://localhost:8082/api/v1/jobs/emp/" + localStorage.getItem('userId'));
        if (response.status === 200) {
          setJobs(response.data);
        } else {
          console.log("Failure");
        }
      } catch (error) {
        console.error('Error fetching jobs:', error);
      }
    };
   
    fetchJobs();
  }, []);
  return (
    <div >
          <Sidebar menuItems={employerMenuItems} />
          <div className='parent-container'>

          <div className='body'>
            <h1> My Jobs Posted </h1>
            {jobs.map((job) => (
              <div key={job.jobId} className='job-card'>
                <h3>Title:  <br/> {job.jobTitle}</h3>
                <p>{job.jobDescription}</p>
                <p>Location: <br/> {job.jobLocation}</p>
              </div>
            ))}
          </div>
        </div>
    </div>
  )
}

export default EmployerDashboard
