import React, {useEffect , useState} from 'react'
import Sidebar from '../Sidebar/Sidebar'
import axios from 'axios';
import './index.css'
function ViewApplicants() {
  const employerMenuItems = [
    { label: 'Dashboard', link: '/employer/dashboard' },
    { label: 'View Applicants', link: '/applicants' },
    { label: 'Create Job Postings', link: '/create-job' },
    

  ]
  const[applications, setApplications] = useState([]);
  useEffect(() => {
    const fetchJobs = async () => {
      try {
        const response = await axios.get("http://localhost:8084/api/v1/application/" + localStorage.getItem('userId'));
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
  return (
    <div >
       <Sidebar menuItems={employerMenuItems} />
          <div className='parent-container'>

          <div className='body'>
            <h1> Applicants applied </h1>
            {applications.map((application) => (
              <div key={application.appId} className='job-card'>
                <h3>Applicant UserId :  <br/> {application.userId}</h3>
              </div>
            ))}
          </div>
        </div>
    </div>
  )
}

export default ViewApplicants
