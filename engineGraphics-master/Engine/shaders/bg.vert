#version 330 core

 layout (location = 0) in vec4 position;
 layout (location = 1) in vec2 tc;
 uniform mat4 ml_matrix;
 uniform float iTime;

 out DATA
 {
 	vec2 tc;
 	vec3 position;
 } vs_out;

 void main()
 {

 	vs_out.tc = vec2(iTime);
 	vs_out.position = vec3(ml_matrix*position);
 	gl_Position = position;

 }