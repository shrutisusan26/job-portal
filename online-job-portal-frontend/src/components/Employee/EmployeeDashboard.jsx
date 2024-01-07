import React, { useEffect, useState } from 'react'
import Sidebar from '../Sidebar/Sidebar'
import axios from 'axios';
import './index.css'

function EmployeeDashboard() {
  const[jobs, setJobs] = useState([]);
  const employeeMenuItems = [
    { label: 'Dashboard', link: '/employee/dashboard' },
    { label: 'Apply To Job ', link: '/apply' },
    { label: 'My Applications', link: '/my-applications' },

  ]
  useEffect(() => {
    const fetchJobs = async () => {
      try {
        const response = await axios.get("http://localhost:8082/api/v1/jobs");
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
       
        <Sidebar menuItems={employeeMenuItems} />
        <div className='parent-container'>
          <div className='body'>
            <h1> All Jobs Posted </h1>
            {jobs.map((job) => (
              <div key={job.jobId} className='job-card'>
                <h3>Title:  <br/> {job.jobTitle}</h3>
                <p>{job.jobDescription}</p>
                <p>Location: <br/> {job.jobLocation}</p>
                <span> Employer Id: {job.userId} </span>
                <span> Job Id: {job.jobId} </span>

              </div>
            ))}
          </div>
        </div>
    </div>
  );
}

export default EmployeeDashboard;
