import React, {useState,useContext} from 'react'
import Sidebar from '../Sidebar/Sidebar'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../../UserContext'; // Adjust the import path based on your file structure

function CreateJob() {
  const employerMenuItems = [
    { label: 'Dashboard', link: '/employer/dashboard' },
    { label: 'View Applicants', link: '/applicants' },
    { label: 'Create Job Postings', link: '/create-job' },

  ]
  const navigate = useNavigate();
  const {setUserId,userId} = useContext(UserContext);

  const [form, setForm] = useState({
    jobTitle: '',
    jobDescription: '',
    jobLocation: '',
    userId: '',
});
const handleSubmit = async () => {
  
    const response = await axios.post("http://localhost:8082/api/v1/jobs", {
        jobTitle: form.jobTitle,
        jobDescription: form.jobDescription,
        jobLocation: form.jobLocation,
        userId: localStorage.getItem('userId'),
      });

      if(response.status === 201) {
            navigate("/employer/dashboard")
    }
      else{
        console.log("failure")
      }
}
  const handleInputChange = (e) =>{
    setForm({...form, [e.target.name]: e.target.value})
}
  return (
    <div >
        <Sidebar menuItems={employerMenuItems} />
        <div className='body'>
        < div className="container">
        <div className='header'>
            <div className='text'>Create a Job Posting </div>
            <div className='underline-company'></div>
        </div>
        <div className='inputs'>
            <div className='input'>
                <img src= "" alt="" />
                <input type="text" name="jobTitle" onChange={handleInputChange} placeholder='Job Title'/>
            </div>
            <div className='input'>
                <img src= "" alt="" />
                <input type="text" name="jobDescription" onChange={handleInputChange} placeholder='Job Description'/>
            </div>
            <div className='input'>
                <img src= "" alt="" />
                <input type="text" name="jobLocation" onChange={handleInputChange} placeholder='Job Location'/>
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

export default CreateJob
