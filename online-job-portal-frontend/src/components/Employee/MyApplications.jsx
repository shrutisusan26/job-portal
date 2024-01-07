import React, { useEffect, useState } from 'react'
import Sidebar from '../Sidebar/Sidebar'
import axios from 'axios';
import './index.css'

function MyApplications() {
  const [applications, setApplications] = useState([]);
  const [jobDetails, setJobDetails] = useState({});
  const employeeMenuItems = [
    { label: 'Dashboard', link: '/employee/dashboard' },
    { label: 'Apply To Job ', link: '/apply' },
    { label: 'My Applications', link: '/my-applications' },
  ];

  useEffect(() => {
    const fetchJobs = async () => {
      try {
        const response = await axios.get("http://localhost:8084/api/v1/application/my-application/1");
        if (response.status === 200) {
          setApplications(response.data);
        } else {
          console.log("Failure");
        }
      } catch (error) {
        console.error('Error fetching jobs:', error);
      }
    };

    fetchJobs();
  }, []);
  useEffect(() => {
    const fetchJobDetails = async (jobId) => {
      try {
        const response = await axios.get(`http://localhost:8082/api/v1/jobs/${jobId}`);
        if (response.status === 200) {
          setJobDetails((prevJobDetails) => ({
            ...prevJobDetails,
            [jobId]: response.data,
          }));
        } else {
          console.log("Failure");
        }
      } catch (error) {
        console.error(`Error fetching job details for jobId ${jobId}:`, error);
      }
    };

    // Fetch job details for each application
    applications.forEach((application) => {
      fetchJobDetails(application.jobId);
    });
  }, [applications]);

  return (
    <div>
        <Sidebar menuItems={employeeMenuItems} />
        <div className='parent-container'>

        <div className='body'>
          <h1> All Jobs Posted </h1>
          {applications.map((application) => (
            <div key={application.appId} className='job-card'>
              <h3>Employer Id:  <br/> {application.empId}</h3>
              <p>Job Id: {application.jobId}</p>
              {jobDetails[application.jobId] && (
                <div>
                  <p>Job Title: {jobDetails[application.jobId].jobTitle}</p>
                  <p>Job Description: {jobDetails[application.jobId].jobDescription}</p>
                  {/* Add more job details as needed */}
                </div>
              )}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default MyApplications
