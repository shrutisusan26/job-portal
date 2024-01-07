import React, {useEffect,useState,useContext} from 'react'
import Sidebar from '../Sidebar/Sidebar';
import { useNavigate} from 'react-router-dom';
import { UserContext } from '../../UserContext';
import axios from 'axios';

function ApplyJob() {

const navigate = useNavigate();
const {setUserId,userId} = useContext(UserContext);
const employeeMenuItems = [
    { label: 'Dashboard', link: '/employee/dashboard' },
    { label: 'Apply To Job ', link: '/apply' },
    { label: 'My Applications', link: '/my-applications' },

  ]
const [employers, setEmployers] = useState([]);
const [jobs, setJobs] = useState([]);
const [form, setForm] = useState({
    userId: localStorage.getItem('userId'),
    empId: '', // To store the selected employer's ID
    jobId: '',
  });
const handleSubmit = async () => {
    const response = await axios.post("http://localhost:8084/api/v1/application/" + form.empId + "/" + form.jobId + "/" + form.userId);

      if(response.status === 200) {
            navigate("/employee/dashboard")
    }
      else{
        console.log("failure")
      }

}
useEffect(()=>{
    axios.get('http://localhost:8081/api/v1/employer')
    .then(response=> {
        setEmployers(response.data);
    })
    .catch(error => {
        console.log('Error fetching employers' , error);
    })
},[]);
const handleEmployerSelection = (e) =>{
    const selectedEmpId = e.target.value;
    setForm({...form , empId: selectedEmpId});

    axios.get(`http://localhost:8082/api/v1/jobs/emp/${selectedEmpId}`)
      .then(response => {
        setJobs(response.data);
      })
      .catch(error => {
        console.error('Error fetching jobs for employer:', error);
      });
}
const handleJobSelection = (e) =>{
    const selectedJobId = e.target.value;
    console.log(selectedJobId);
    setForm({...form , jobId: selectedJobId});

}
return (
<div >
    <Sidebar menuItems={employeeMenuItems} />
    <div className='body'>
    < div className="container">
    <div className='header'>
        <div className='text'>Apply To Job </div>
        <div className='underline-company'></div>
    </div>
    <div className='inputs'>
        <div className='input'>
            <select name="empId" onChange={handleEmployerSelection} >
                <option value = "" disabled> Select Employer</option>
                {employers.map(employer => ( 
                    <option key={employer.empId} value={employer.empId}>
                        {employer.companyName}
                    </option>
                ))}
            </select>
        </div>
        <div className='input'>
            <select name="empId" onChange={handleJobSelection} >
                <option value = "" disabled> Select Job</option>
                {jobs.map(job => ( 
                    <option key={job.jobId} value={job.jobId}>
                        {job.jobTitle}
                    </option>
                ))}
            </select>
        </div>
    </div>
    <div className='submit-container'>
        <div  className='submit' onClick={handleSubmit} >Submit</div>
    </div>
</div>
    </div>
</div>
)
}

export default ApplyJob
