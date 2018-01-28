#version 330 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 tc;

uniform mat4 ml_matrix;
uniform float iTime;

out DATA
{
	vec2 tc;
	vec2 time;
} vs_out;

void main()
{
	vs_out.tc = tc;
	vs_out.time = vec2(float(iTime/10000.0f));
	gl_Position = ml_matrix * position;

}