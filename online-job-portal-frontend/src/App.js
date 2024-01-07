import LoginSignUp from '../src/components/LoginSignup/LoginSignUp';
import './App.css';
import React, {useState } from 'react'
import { BrowserRouter , Routes, Route } from 'react-router-dom';
import EmployerDashboard from './components/Employer/EmployerDashboard';
import EmployeeDashboard from './components/Employee/EmployeeDashboard';
import RegisterEmployer from './components/Employer/RegisterEmployer';
import ViewApplicants from './components/Employer/ViewApplicants';
import CreateJob from './components/Employer/CreateJob';
import { UserProvider } from './UserContext';
import ApplyJob from './components/Employee/ApplyJob';
import MyApplications from './components/Employee/MyApplications';
function App() {

  return (
    <UserProvider>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginSignUp/>}/>
        <Route path="/dashboard" element={<EmployeeDashboard/>}/>
        <Route path="/register/employer" element={<RegisterEmployer/>}/>
        <Route path="/employer/dashboard" element={<EmployerDashboard/>}/>
        <Route path="/create-job" element={<CreateJob/>}/>
        <Route path="/applicants" element={<ViewApplicants/>}/>
        <Route path="/employee/dashboard" element={<EmployeeDashboard/>}/>
        <Route path="/apply" element={<ApplyJob/>} />
        <Route path="/my-applications" element={<MyApplications/>}/>
      </Routes>
    </BrowserRouter>
    </UserProvider>

  );
}

export default App;
